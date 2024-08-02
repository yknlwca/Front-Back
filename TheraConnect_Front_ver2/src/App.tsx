import React from "react";
import logo from "./logo.svg";
import "./App.css";
import ThMainPage from "./pages/ThMainPage";
import ButtonUsage from "./components/ButtonUsage";

function App() {
  return (
    <div className="App">
      <ThMainPage />
      <ButtonUsage />
    </div>
  );
}

export default App;
