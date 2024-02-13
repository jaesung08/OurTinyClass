
import { useCallback,  useEffect,  useRef, useState } from 'react';
import { DailyProvider, useCallFrame, useDaily } from '@daily-co/daily-react';
import DailyIframe from '@daily-co/daily-js';
import { useRecoilValue } from 'recoil';
import { userState } from '@/atoms/user';
import { Button } from '@nextui-org/react';
import { useNavigate } from 'react-router-dom';
export default function Video() {
  let callRef = useRef<HTMLDivElement>(null);
  const id = "test_room1";
  const domain = "https://ssafya.daily.co/";
  const userInfo = useRecoilValue(userState);
  const navigator = useNavigate();
  const callFrame = useCallFrame({
    parentEl: callRef.current,
    options: {
      iframeStyle: {
        position: 'absolute',
        width: '100%',
        height: '100%',
      },
      showLeaveButton: true,
      showFullscreenButton: true,
      userName: userInfo.name,
    },
    shouldCreateInstance: useCallback(() =>  Boolean(callRef.current) , []),
  });
  const [callReady, setCallReady] = useState(false); // react가 callFrame의 변경 감지를 못하므로, 강제로 리렌더링 해주기 위한 state
  useEffect(() => {
    setCallReady(true);
    callFrame?.join({ url: `${domain}${id}` });
  }, [callFrame])

  const leave = async () => {
    setCallReady(false);
    await callFrame?.leave();
    await callFrame?.destroy();
    navigator("/")
  }

  callFrame?.on('left-meeting', leave)  
  return (
    <DailyProvider callObject={callFrame}>
      <div ref={callRef}/>
    </DailyProvider>
  )
}
