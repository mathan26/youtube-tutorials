import axios from 'axios';
import { redirect } from 'react-router';

export const getAllProducts = async () => {
    const response = await axios.get('https://dummyjson.com/products');
    console.log(response);
    return response.data;
};

export const getSingleProduct = async ({ params }) => {
    console.log(params);
    const response = await axios.get(`https://dummyjson.com/products/${params.id}`);
    console.log(response);
    return response.data;
};

export const getProductByCategory = async (categoryName) => {
    const response = await axios.get(`https://dummyjson.com/products/category/${categoryName}`);
    console.log(response);
    return response.data;
};

export const submitForm = async ({ request }) => {
    const formData = await request.formData();
    const name = formData.get('search');
    console.log('Form submitted with name:', name);
    return redirect(`/products/search?search=${name}`);
};
