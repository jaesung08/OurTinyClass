import { useRecoilState, useRecoilValue, useSetRecoilState } from "recoil";
import { chatListAtom, currentRoomIdAtom, roomList, subscribeListAtom } from "@/atoms/chatRoom";
import { userState } from "@/atoms/user";
import { Chat } from "@/feature/chat/types";
type StompClientProps = {
	children: React.ReactNode;
};
import { IMessage, useSubscription } from "react-stomp-hooks";
import { getChatRoom, getChatRoomList } from "@/feature/chat/api/chatRoom";
import { useEffect } from "react";

type ReceveMessageBody = {
	chatMessageType: "MESSAGE" | "SUBSCRIBE";
} & Chat;

export const StompClientProvider = ({ children }: StompClientProps) => {
	const userInfo = useRecoilValue(userState);
	const currentRoomId = useRecoilValue(currentRoomIdAtom);
	const [chatList, setChatList] = useRecoilState(chatListAtom);
	const setChatRoomList = useSetRecoilState(roomList);
	const [subscribeList, setSubscribeList] = useRecoilState(subscribeListAtom);
	useSubscription(subscribeList, (message: IMessage) => recevHandler(message));

	function recevHandler(message: IMessage) {
		const messageBody: ReceveMessageBody = JSON.parse(message.body);
		switch (messageBody.chatMessageType) {
			case "MESSAGE":
				onRecevMessage(messageBody);
				break;
			case "SUBSCRIBE":
				onRecevRoomInvite(messageBody.roomId);
				break;
		}
		if (chatList && currentRoomId == messageBody.roomId) {
			setChatList([...chatList, messageBody]);
		}
	}

	function onRecevMessage(message: ReceveMessageBody) {
		if (chatList && currentRoomId == message.roomId) {
			setChatList([
				...chatList,
				{
					senderId: message.senderId,
					chatId: message.chatId,
					roomId: message.roomId,
					message: message.message,
					createdAt: message.createdAt,
					senderName: message.senderName,
				},
			]);
		} else {
			if (Notification.permission === "granted") {
				// 알림 생성
				new window.Notification(message.senderName, { body: message.message });
			} else {
				console.log("이 시스템에서는 알림이 지원되지 않습니다.");
			}
		}
	}

	async function onRecevRoomInvite(roomId: string) {
		setSubscribeList((beforeList) => [...beforeList, "/sub/room/" + roomId]);
		try {
			const res = await getChatRoom(roomId);
			setChatRoomList((_roomList) => [..._roomList, res.data]);
		} catch (e) {
			console.error(e);
		}
	}

	useEffect(() => {
		if (Notification.permission !== "granted") {
			Notification.requestPermission();
		} else {
			new window.Notification("Tㅁㄴㅇ", { body: "Aaa" });
		}
	}, []);

	useEffect(() => {
		if (userInfo && !subscribeList.length) {
			setSubscribeList(["/sub/room/" + userInfo.memberId]);
		}
	}, [setSubscribeList, subscribeList.length, userInfo]);

	useEffect(() => {
		const fetchChatRoomList = async () => {
			try {
				const res = await getChatRoomList();
				setChatRoomList(res.data);
				setSubscribeList(["/sub/room/" + userInfo.memberId, ...res.data.map((chatRoom) => "/sub/room/" + chatRoom.roomId)]);
			} catch (e) {
				console.error(e);
			}
		};
		fetchChatRoomList();
	}, [setChatRoomList, setSubscribeList, userInfo.memberId]);

	return <>{children}</>;
};
