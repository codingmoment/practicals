import React from "react";
import AuthContext from "../../store/AuthContext";

import classes from "./Navigation.module.css";

const Navigation = () => {
  // You can use React hook useContext
  // The value returned is the context that you can use directly in JSX.
  // See Home.js
  // const context = useContext(AuthContext);

  return (
    <AuthContext.Consumer>
      {(ctx) => {
        return (
          <nav className={classes.nav}>
            <ul>
              {ctx.isLoggedIn && (
                <li>
                  <a href="/">Users</a>
                </li>
              )}
              {ctx.isLoggedIn && (
                <li>
                  <a href="/">Admin</a>
                </li>
              )}
              {ctx.isLoggedIn && (
                <li>
                  <button onClick={ctx.onLogout}>Logout</button>
                </li>
              )}
            </ul>
          </nav>
        );
      }}
    </AuthContext.Consumer>
  );
};

export default Navigation;
