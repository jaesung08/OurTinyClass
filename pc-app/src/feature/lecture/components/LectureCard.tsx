import {
  Card,
  CardHeader,
  CardBody,
  CardFooter,
  Avatar,
  Button,
} from "@nextui-org/react";

export function LectureCard() {
  return (
    <Card className="max-w-96">
      <div className="flex">
        <CardHeader className="flex flex-col gap-5 w-2/6">
          <Avatar
            isBordered
            radius="full"
            size="lg"
            src="https://cdn.imweb.me/upload/S20220826948cbdc34dca3/a7e21622bc1f0.png"
          />
          <div className="flex flex-col gap-1 items-start justify-center">
            <h4 className="text-small font-semibold leading-none text-default-600">
              Zoey Lang
            </h4>
            <h5 className="text-small tracking-tight text-default-400">
              @zoeylang
            </h5>
          </div>
        </CardHeader>
        <CardBody className="px-3 py-0 text-small text-default-400 w-4/6 justify-center">
          <p>
            Frontend developer and UI/UX enthusiast. Join me on this coding
            adventure!
          </p>
          <span className="pt-2">
            #FrontendWithZoey
            <span className="py-2" aria-label="computer" role="img">
              💻
            </span>
          </span>
        </CardBody>
      </div>
      <CardFooter className="justify-around gap-3">
        <Button className="w-3/6 bg-white b">❤️</Button>
        <Button className="w-3/6 bg-white b">✔️</Button>
      </CardFooter>
    </Card>
  );
}
