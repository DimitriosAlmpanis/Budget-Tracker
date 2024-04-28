import axios from "axios";

const REST_API_BASE_URL = 'http://localhost:8080/api/budgettracker/';

export const createTransaction = (transaction) => {
    return axios.post(REST_API_BASE_URL+"transaction", transaction);
}

export const listTransactions = () => {
    console.log("TransactionService - listTransactions")
    return axios.get(REST_API_BASE_URL+"transactions");
}

export const getTransaction = (transactionID) => {
    return axios.get(REST_API_BASE_URL + "transaction/" + transactionID);
}

export const updateTransaction = (transactionID, transaction) => {
    return axios.put(REST_API_BASE_URL + "transaction/" + transactionID, transaction)
}

export const deleteTransaction = (transactionID) => {
    return axios.delete(REST_API_BASE_URL + "transaction/" + transactionID);
}