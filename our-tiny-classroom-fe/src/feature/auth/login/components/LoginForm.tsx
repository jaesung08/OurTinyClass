/**
 * v0 by Vercel.
 * @see https://v0.dev/t/idSQHuphTh3
 */

import { Button, Input, Link } from "@nextui-org/react";
import { useCallback, useState } from "react";
import { LoginFormProps } from "../types";

export default function LoginForm({ onSubmit }: LoginFormProps) {
  const [inputId, setInputId] = useState("");
  const [inputPassword, setInputPassword] = useState("");
  const onChangeInputId = useCallback(
    (e: React.ChangeEvent<HTMLInputElement>) => {
      setInputId(e.target.value);
    },
    []
  );
  const onChangeInputPassword = useCallback(
    (e: React.ChangeEvent<HTMLInputElement>) => {
      setInputPassword(e.target.value);
    },
    []
  );
  const onSubmitForm = useCallback(
    (e: React.SyntheticEvent<HTMLFormElement>) => {
      onSubmit(inputId, inputPassword);
      setInputId("");
      setInputPassword("");
      e.preventDefault();
    },
    [inputId, inputPassword, onSubmit]
  );
  return (
    <form
      action="#"
      className="space-y-6"
      method="POST"
      data-testid="LoginForm"
      onSubmit={onSubmitForm}
    >
      <div>
        <label htmlFor="input-id">id</label>
        <Input
          data-testid="LoginFormInputId"
          autoComplete="input-id"
          className="mt-1 block w-full"
          id="input-id"
          name="input-id"
          required
          value={inputId}
          onChange={onChangeInputId}
        />
      </div>
      <div>
        <label htmlFor="password">Password</label>
        <Input
          value={inputPassword}
          onChange={onChangeInputPassword}
          data-testid="LoginFormInputPassword"
          autoComplete="current-password"
          className="mt-1 block w-full"
          id="password"
          name="password"
          required
          type="password"
        />
      </div>
      <div className="flex items-center justify-between">
        <div className="flex items-center">
          <Link
            className="font-medium text-green-600 hover:text-green-500"
            href="#"
          >
            Forgot your password?
          </Link>
        </div>
      </div>
      <div>
        <Button
          className="w-full bg-green-500 hover:bg-green-700 text-white"
          type="submit"
        >
          Sign in
        </Button>
      </div>
    </form>
  );
}
