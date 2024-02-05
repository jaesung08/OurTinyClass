import { useState, useEffect } from "react";
import { Button } from "@nextui-org/button";
import { EventSourcePolyfill, NativeEventSource } from "event-source-polyfill";
import Cookies from "js-cookie";

export const SocketTest = () => {
  const [currentMessage, setCurrentMessage] = useState();
  const EventSource = EventSourcePolyfill || NativeEventSource;
  const testCookie = Cookies.get("accessToken");
  // 문자열을 공백을 기준으로 분할
  const splitValues = testCookie.split(" ");

  // 분할된 값 중 1번 인덱스의 값을 가져옴
  const desiredValue = splitValues[1];
  useEffect(() => {
    const eventSource = new EventSource(
      "http://70.12.246.222:8080/api/notification/stream",
      {
        headers: {
          Authorization: `Bearer ${desiredValue}`, // 여기에 액세스 토큰을 추가
        },
      }
    );
    console.log(eventSource);
    const handleEvent = (event) => {
      return event;
    };

    const handleError = (error) => {
      console.error("EventSource failed:", error);
      eventSource.close();
    };

    setCurrentMessage((eventSource.onmessage = handleEvent));
    eventSource.onerror = handleError;

    // 컴포넌트가 언마운트되면 EventSource를 닫음
    return () => {
      eventSource.close();
    };
  }, []);

  return (
    <section className="w-full bg-black flex justify-center items-center h-lvh">
      <div className="w-3/6 h-3/6 bg-white flex flex-col justify-between">
        {currentMessage ? <p>{currentMessage}</p> : "없습니다."}
      </div>
    </section>
  );
};
