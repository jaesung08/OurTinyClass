import { screen } from "@testing-library/react";
import Login from "../routes/Login";
import { render } from "@/_tests_/testUtils";

describe("<Login />", () => {
  const setup = () => {
    const onSubmit: (id: string, password: string) => void = jest.fn();

    render(<Login />);
    screen.getByText("Sign in");
    const inputId = screen.getByTestId("LoginFormInputId");
    const inputPassword = screen.getByTestId("LoginFormInputPassword");
    const loginForm = screen.getByTestId("LoginForm");
    const submitButton = screen.getByText("Sign in");
    return { inputId, inputPassword, loginForm, submitButton, onSubmit };
  };
  it("should be rendered", () => {
    setup();
  });
});
