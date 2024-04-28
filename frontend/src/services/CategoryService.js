import axios from "axios";

const REST_API_BASE_URL = 'http://localhost:8080/api/budgettracker/';

export const createCategory = (category) => {
    return axios.post(REST_API_BASE_URL+"category", category);
}

export const listCategories = () => {
    console.log("CategoryService - listCategories")
    return axios.get(REST_API_BASE_URL+"categories");
}

export const getCategory = (categoryID) => {
    return axios.get(REST_API_BASE_URL + "category/" + categoryID);
}

export const updateCategory = (categoryID, category) => {
    return axios.put(REST_API_BASE_URL + "category/" + categoryID, category)
}

export const deleteCategory = (categoryID) => {
    return axios.delete(REST_API_BASE_URL + "category/" + categoryID);
}