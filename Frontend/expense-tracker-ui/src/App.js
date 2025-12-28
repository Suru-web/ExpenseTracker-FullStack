import { useAuth } from "./auth/AuthContext";
import Login from "./pages/Login";
import Expenses from "./pages/Expenses";

function App() {
  const { token } = useAuth();
  return token ? <Expenses /> : <Login />;
}

export default App;