import { useContext, useEffect, useState } from "react";

import CartIcon from "./CartIcon";
import CartContext from "../../store/cart-context";
import classes from "./HeaderCartButton.module.css";

const HeaderCartButton = (props) => {
  const [buttonIsHighligthted, setButtonIsHighligthted] = useState(false);

  const cartCtx = useContext(CartContext);

  const numberOfCartItems = cartCtx.items.reduce((lastAmount, item) => {
    return lastAmount + item.amount;
  }, 0);

  const buttonClasses = `${classes.button} ${
    buttonIsHighligthted ? classes.bump : ""
  }`;

  const { items } = cartCtx;

  useEffect(() => {
    if (cartCtx.items.length === 0) {
      return;
    }
    setButtonIsHighligthted(true);

    const timer = setTimeout(() => {
      setButtonIsHighligthted(false);
    }, 300);

    return () => {
      clearTimeout(timer);
    };
  }, [items]);

  return (
    <button className={buttonClasses} onClick={props.onClick}>
      <span className={classes.icon}>
        <CartIcon />
      </span>
      <span>Your Cart</span>
      <span className={classes.badge}>{numberOfCartItems}</span>
    </button>
  );
};

export default HeaderCartButton;
