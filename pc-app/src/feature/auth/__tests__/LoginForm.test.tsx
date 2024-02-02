import "@testing-library/jest-dom";
import { fireEvent, screen, waitFor } from "@testing-library/react";
import { render } from "@/_tests_/testUtils";
import userEvent from "@testing-library/user-event";
import LoginForm from "../components/LoginForm";

describe("<LoginForm />", () => {
  const setup = () => {
    const onSubmit: (id: string, password: string) => void = jest.fn();
    render(<LoginForm onSubmit={onSubmit} />);
    screen.getByText("Sign in");
    const inputId = screen.getByTestId("LoginFormInputId");
    const inputPassword = screen.getByTestId("LoginFormInputPassword");
    const loginForm = screen.getByTestId("LoginForm");
    const submitButton = screen.getByText("Sign in");
    return { inputId, inputPassword, loginForm, submitButton, onSubmit };
  };
  it("shoud be rendered", () => {
    setup();
  });

  it("changes input ID", async () => {
    const { inputId } = setup();

    userEvent.type(inputId, "ssafy");
    await waitFor(() => {
      expect(inputId).toHaveValue("ssafy");
    });
  });

  it("changes input PW", async () => {
    const { inputPassword } = setup();

    userEvent.type(inputPassword, "123456");
    await waitFor(() => {
      expect(inputPassword).toHaveValue("123456");
    });
  });

  it("form을 제출했을 때, onSubmit 함수가 실행되어야 한다.", async () => {
    const { inputId, inputPassword, submitButton, onSubmit } = setup();

    fireEvent.change(inputId, { target: { value: "ssafy" } });
    fireEvent.change(inputPassword, { target: { value: "123456" } });

    fireEvent.click(submitButton);
    expect(onSubmit).toHaveBeenCalledWith("ssafy", "123456");
  });

  it("form을 제출했을 때, id와 password input이 비워져야 한다.", async () => {
    const { inputId, inputPassword, submitButton } = setup();

    fireEvent.change(inputId, { target: { value: "ssafy" } });
    fireEvent.change(inputPassword, { target: { value: "123456" } });

    fireEvent.click(submitButton);

    expect(inputId).toHaveValue("");
    expect(inputPassword).toHaveValue("");
  });
});
