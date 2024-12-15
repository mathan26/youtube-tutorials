import React from 'react';
import { useCart } from '../../context/CartContext.jsx';

const Cart = () => {
    const { state } = useCart();
    return (
        <div>
            <h1 className="text-2xl font-bold mb-4">Your Cart</h1>
            <ul className="space-y-3">
                {state.cart.map((item, index) => (
                    <li
                        key={index}
                        className="flex justify-between items-center p-4 border rounded shadow-md"
                    >
                        <div>{item.title}</div>
                        <div>{item.price}</div>
                    </li>
                ))}
            </ul>
        </div>
    );
};

export default Cart;
