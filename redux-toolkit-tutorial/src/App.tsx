import { Provider } from "react-redux";
import Counter from "./components/counter/Counter";
import { store } from "./store";
import UserComponent from "./components/user/UserComponent";

const App = () => {
  return (
    <Provider store={store}>
      <div className="bg-gray-100 flex  justify-center items-center gap-5">
        <Counter />
        <UserComponent />
      </div>
    </Provider>
  );
};

export default App;
