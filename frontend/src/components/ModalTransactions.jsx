import React, {useState, useEffect} from 'react'

import { createTransaction, updateTransaction ,getTransaction } from '../services/TransactionService'

import "./Modal.css"

function ModalTransactions(props) {

    const [amount, setAmount] = useState('')
    const [category, setCategory] = useState({id: "",name: ""})
    const [date, setDate] = useState('')
    const [description, setDescription] = useState('')

    const [errors, setErrors] = useState({
        amount: '',
        category: {id: 0, name: ""},
        date: '',
        description: ''
    })

    useEffect(() => {
        if(props.id) {
            getTransaction(props.id).then((response) => {
                setAmount(response.data.amount);
                setCategory(response.data.category);
                setDate(handleDate(response.data.date));
                setDescription(response.data.description);
            }).catch(error => {
                console.error(error);
            })
        }
    },[props.id])

    function handleCategory(event) {
        props.categories.forEach(c => {
            if (c.id == event.target.value) {
                setCategory(c);
            }
        });
    }

    function handleDate(d) {
        //Convert to yyyy-MM-ddThh:mm
        let dateStr = d.substr(0,16)
        console.log("Returning date: " + dateStr)
        return dateStr;
    }

    function addOrEditTransaction(e) {
        e.preventDefault();

        if (validateForm()) {

            const transaction = {amount,category,date,description}
            console.log("Validation - True")
            console.log(transaction)

            if (props.id) {
                updateTransaction(props.id, transaction).then((response) => {
                    console.log(response.data);
                }).catch((error) => {
                    console.error(error);
                })
            } else {
                createTransaction(transaction).then((response) => {
                    console.log(response.data);
                }).catch(error => {
                    console.error(error);
                })
            }
            props.closeTransactionModal(false);
        }
    }

    function validateForm() {
        let valid = true;
        const errorsCopy = {... errors};

        if (amount != 0) {
            errorsCopy.amount = '';
        } else {
            errorsCopy.amount = 'Amount is required.';
            valid = false;
        }

        if (category.name.trim()) {
            errorsCopy.category.name = '';
        } else {
            errorsCopy.category.name = 'Category is required.';
            valid = false;
        }
        
        if (date.trim()) {
            errorsCopy.date = '';
        } else {
            errorsCopy.date = 'Date is required.';
            valid = false;
        }
        setErrors(errorsCopy);

        return valid;
    }

    function pageTitle() {
        if(props.id) {
            return <h2 className='modal-title'>Edit Transaction</h2>;
        } else {
            return <h2 className='modal-title'>Add Transaction</h2>;
        }
    }

    return (
        <div className='modal-background'>
            <div className='modal-container'>
                <div className='close-btn'>
                    <button id='x-btn' onClick={() => props.closeTransactionModal(false)}>╳</button>
                </div>
                <div className='modal-title-container'>
                    {pageTitle()}
                </div>
                <div className='modal-body'>
                    <form>
                        <div id='amount-field-container'>
                            <label htmlFor='amount-field'
                            id="amount-label"
                            className='required-label'>Amount</label>
                            <input
                            type="number"
                            name='amount-field'
                            id = 'amount-field'
                            value={amount}
                            className={`transaction-field ${errors.amount ? 'required-field':''}`}
                            onChange={(e) => setAmount(e.target.value)}
                            />
                            
                        </div>
                        <div className='amount-error'>{errors.amount}</div>
                        <div id='category-field-container'>
                            <label htmlFor='category-field'
                            id="category-label"
                            className='required-label'>Category</label>
                            <select
                            name='category-field'
                            value={category.id}
                            id = 'category-field'
                            className={`transaction-field ${errors.category.name ? 'required-field':''}`}
                            onChange={handleCategory}>
                                <option value="" disabled hidden></option>
                                {
                                    props.categories.map((cat,index) =>
                                        <option value={cat.id} key={cat.id}>{cat.name}</option>
                                    )                          
                                }
                            </select>
                        </div>
                        <div>
                        <div className='category-error'>{errors.category.name}</div>
                        </div>
                        <div id='date-field-container'>
                            <label htmlFor='date-field'
                            id="date-label"
                            className='required-label'>Date</label>
                            <input
                            type="datetime-local"
                            name='date-field'
                            value={date}
                            id = 'date-field'
                            className={`transaction-field ${errors.date ? 'required-field':''}`}
                            onChange={(e) => setDate(e.target.value)}
                            >
                            </input>
                        </div>
                        <div className='date-error'>{errors.date}</div>
                        <div className='description-field-container'>
                            <label htmlFor='description-field'
                            id="description-label">Description</label>
                            <input
                            type="text"
                            name='description-field'
                            value={description}
                            id='description-field'
                            onChange={(e) => setDescription(e.target.value)}
                            >
                            </input>
                        </div>
                        <div className='footer-buttons'>
                            <button
                            className='modal-buttons'
                            id='footer-btn-submit'
                            onClick={addOrEditTransaction}
                            >Submit</button>
                        </div>
                    </form> 
                </div>
            </div>
        </div>
    )
}

export default ModalTransactions