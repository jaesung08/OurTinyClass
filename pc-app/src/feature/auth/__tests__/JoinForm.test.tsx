import "@testing-library/jest-dom";
import { fireEvent, screen, waitFor } from "@testing-library/react";
import { render } from "@/_tests_/testUtils";
import userEvent from "@testing-library/user-event";
import JoinForm from "../components/JoinForm";

describe("<JoinForm />", () => {
  beforeEach(() => {
    jest.spyOn(console, "log");
  });

  afterEach(() => {
    jest.restoreAllMocks();
  });
  const setup = () => {
    const onSubmit: (
      id: string,
      password: string,
      name: string,
      email: string,
      birthday: string
    ) => void = jest.fn();
    render(<JoinForm onSubmit={onSubmit} />);
    screen.getByText("회원가입");

    const joinForm = screen.getByTestId("JoinForm");
    const inputId = screen.getByTestId("JoinFormInputId");
    const inputPassword = screen.getByTestId("JoinFormInputPassword");
    const inputPasswordConfirm = screen.getByTestId(
      "JoinFormInputPasswordConfirm"
    );
    const inputName = screen.getByTestId("JoinFormInputName");
    const inputEmail = screen.getByTestId("JoinFormInputEmail");
    const inputBirthday = screen.getByTestId("JoinFormInputBirthday");
    const submitButton = screen.getByText("확인");
    return {
      joinForm,
      inputId,
      inputPassword,
      inputPasswordConfirm,
      submitButton,
      inputName,
      inputEmail,
      inputBirthday,
      onSubmit,
    };
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
    const {
      inputId,
      inputPassword,
      inputPasswordConfirm,
      submitButton,
      inputName,
      inputEmail,
      inputBirthday,
      onSubmit,
    } = setup();

    fireEvent.change(inputId, { target: { value: "ssafy123" } });
    fireEvent.change(inputPassword, { target: { value: "abc123" } });
    fireEvent.change(inputPasswordConfirm, { target: { value: "abc123" } });
    fireEvent.change(inputName, { target: { value: "김싸피" } });
    fireEvent.change(inputEmail, { target: { value: "ssafy@acv.com" } });
    fireEvent.change(inputBirthday, { target: { value: "1999-01-01" } });

    fireEvent.click(submitButton);

    expect(onSubmit).toHaveBeenCalledWith(
      "ssafy123",
      "abc123",
      "김싸피",
      "ssafy@acv.com",
      "1999-01-01"
    );
  });

  it("form을 제출했을 때, form 내부의 input이 비워져야 한다.", async () => {
    const {
      inputId,
      inputPassword,
      inputPasswordConfirm,
      submitButton,
      inputName,
      inputEmail,
      inputBirthday,
    } = setup();

    fireEvent.change(inputId, { target: { value: "ssafy123" } });
    fireEvent.change(inputPassword, { target: { value: "abc123" } });
    fireEvent.change(inputPasswordConfirm, { target: { value: "abc123" } });
    fireEvent.change(inputName, { target: { value: "김싸피" } });
    fireEvent.change(inputEmail, { target: { value: "ssafy@acv.com" } });
    fireEvent.change(inputBirthday, { target: { value: "1999-01-01" } });

    fireEvent.click(submitButton);

    expect(inputId).toHaveValue("");
    expect(inputPassword).toHaveValue("");
    expect(inputPasswordConfirm).toHaveValue("");
    expect(inputName).toHaveValue("");
    expect(inputEmail).toHaveValue("");
    expect(inputBirthday).toHaveValue("");
  });

  //TODO : 이메일 형식 체크 잘 되는지 테스트

  //TODO : 아이디 조건 체크 잘 되는지 테스트

  it("회원가입이 정상작동 되어야 한다.", async () => {
    const {
      inputId,
      inputPassword,
      inputPasswordConfirm,
      submitButton,
      inputName,
      inputEmail,
      inputBirthday,
    } = setup();

    fireEvent.change(inputId, { target: { value: "ssafy123" } });
    fireEvent.change(inputPassword, { target: { value: "abc123" } });
    fireEvent.change(inputPasswordConfirm, { target: { value: "abc123" } });
    fireEvent.change(inputName, { target: { value: "김싸피" } });
    fireEvent.change(inputEmail, { target: { value: "ssafy@acv.com" } });
    fireEvent.change(inputBirthday, { target: { value: "1999-01-01" } });

    fireEvent.click(submitButton);

    await waitFor(
      () => {
        expect(console.log).toHaveBeenCalledWith("회원가입 성공");
      },
      { timeout: 3000 }
    );
  });
});
