import { useEffect } from "react";
import { AppDispatch, RootState } from "../../store";
import { useDispatch, useSelector } from "react-redux";
import { fetchUser } from "./userSlice";

const UserComponent = () => {
  const dispatch: AppDispatch = useDispatch();

  const { user, loading } = useSelector((state: RootState) => state.user);

  const counterValue = useSelector((state: RootState) => state.counter.value);

  console.log(user);

  useEffect(() => {
    dispatch(fetchUser());
  }, [dispatch]);

  if (loading) {
    return <div>Loading.....</div>;
  }

  return (
    <div className="flex justify-center items-center  min-h-screen">
      <div className="bg-white p-8 rounded-lg shadow-md h-[400px]">
        <h1 className="text-3xl fond-bold text-gray-800 mb-4">User Details</h1>
        <h1 className="text-3xl fond-bold text-gray-800 mb-4">Counter value {counterValue}</h1>

        <div className="flex items-center space-x-4 mb-4">
          <div className="text-gray-500">
            Username: <span className="text-gray-700">{user.name}</span>
          </div>
        </div>
        <div className="flex items-center space-x-4 mb-4">
          <div className="text-gray-500">
            Email: <span className="text-gray-700">{user.email}</span>
          </div>
        </div>
      </div>
    </div>
  );
};

export default UserComponent;
