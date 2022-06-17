import { Route, Routes, Navigate } from "react-router-dom"
import Main from "./components/Main"
import Signup from "./components/Signup"
import Login from "./components/Login"
import Maindetail from "./components/Maindetail/Maindetail"

function App() {
const user = localStorage.getItem("token")
return (
<Routes>
{user && <Route path="/" exact element={<Main />} />}
<Route path="/signup" exact element={<Signup />} />
<Route path="/login" exact element={<Login />} />
< Route path="/Maindetail" exact element={<Maindetail />} />
<Route path="/" element={<Navigate replace to="/login" />} />

</Routes>

)
}
export default App