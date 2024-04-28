import React , {useState, useEffect} from 'react'
import ModalTransactions from './ModalTransactions'
import { createTransaction, listTransactions, deleteTransaction } from '../services/TransactionService'
import { listCategories } from '../services/CategoryService'
import ModalCategories from './ModalCategories'

import bin from '../assets/bin.svg'
import edit from '../assets/edit.svg'

function Transactions() {
    const [categories, setCategories] = useState([])
    const [transactions, setTransactions] = useState([])
    const [openTransactionModal, setOpenTransactionModal] = useState(false)
    const [openCategoryModal, setOpenCategoryModal] = useState(false)
    const [editID, setEditID] =  useState(null)

    function getAllTransactions() {
        listTransactions().then((response) => {
            setTransactions(response.data);
        }).catch(error => {
            console.log(error);
        })
    }

    function getAllCategories() {
        listCategories().then((response) => {
            setCategories(response.data);
        }).catch(error => {
            console.log(error);
        })
    }

    useEffect(() => {
        getAllTransactions();
        getAllCategories();
        console.log("Render Occured.");
    },[openTransactionModal])

    function removeTransaction(id) {
        console.log("Removing transaction with ID: " + id);
        deleteTransaction(id).then(() => {
            getAllTransactions();
        }).catch(error => {
            console.error(error);
        })
    }

    function displayBalance() {
        let b = 0;
        transactions.forEach(t => {
            b += t.amount
        });
        return(
            <h1 id='balance'>{b} €</h1>
        )
    }

    function transformDate(d) {
        let dateStr = d.replace('T',' - ')
        dateStr = dateStr.substr(0,21)
        return dateStr;
    }

    return (
        <div id='transaction-body'>
            <div>
                {displayBalance()}
            </div>
            <section id='transaction-container'>
                <div id='transaction-title'></div>
                <div id='transaction-buttons'>
                    <button
                    className='add-btn'
                    id='add-transaction-btn'
                    onClick={() =>{
                        setOpenTransactionModal(true)
                        setEditID(null)
                    }}
                    >Add Transaction
                    </button>
                    <button
                    className='add-btn'
                    id='add-category-btn'
                    onClick={() => setOpenCategoryModal(true)}
                    >Add Category
                    </button>
                </div>
                {openTransactionModal && <ModalTransactions closeTransactionModal={setOpenTransactionModal} categories={categories} id={editID}/>}
                {openCategoryModal && <ModalCategories closeCategoryModal={setOpenCategoryModal} />}
                <div id='transaction-table-container'>
                    <table>
                        <thead>
                            <tr id='table-headers'>
                                <th>ID</th>
                                <th>Amount</th>
                                <th>Category</th>
                                <th>Date</th>
                                <th>Description</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                transactions.map(transaction =>
                                    <tr key={transaction.id}>
                                        <td>{transaction.id}</td>
                                        <td>{transaction.amount}</td>
                                        <td>{transaction.category.name}</td>
                                        <td>{transformDate(transaction.date)}</td>
                                        <td>{transaction.description}</td>
                                        <td id='actions-container'>
                                            <div id='actions'>
                                                <img id='edit'
                                                src={edit}
                                                alt="Edit"
                                                onClick={() => {
                                                    setOpenTransactionModal(true)
                                                    setEditID(transaction.id)
                                                    console.log("Edit - " + transaction.id)
                                                }}/>   
                                                <img id='bin' 
                                                src={bin} 
                                                alt="Delete"
                                                onClick={() => removeTransaction(transaction.id)} />
                                            </div>
                                        </td>
                                    </tr>
                                )
                            }
                        </tbody>
                    </table>
                </div>

            </section>
        </div>
    )
}

export default Transactions