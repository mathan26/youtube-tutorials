import React, { useContext, useState } from "react";

const CartContext = React.createContext(null);

export const CardProvider = ({ children }) => {
  const [state, setState] = useState({ cart: [] });

  const setCart = (cart) => {
    setState((prevState) => ({
      ...prevState,
      cart: [...prevState.cart, cart],
    }));
  };

  const sharedData = { state, setCart };

  return (
    <CartContext.Provider value={sharedData}>{children}</CartContext.Provider>
  );
};

export const useCart = () => {
  return useContext(CartContext);
};
