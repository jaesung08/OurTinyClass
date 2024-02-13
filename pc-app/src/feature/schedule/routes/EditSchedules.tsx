import ScheduleBoard from "../components/ScheduleBoard";

function EditSchedules() {
  return (
    <div className=" h-full">
      <h1>일정 관리</h1>
      <div className=" h-[80%]">
        <ScheduleBoard editMode={true} />;
      </div>
    </div>
  );
}

export default EditSchedules;