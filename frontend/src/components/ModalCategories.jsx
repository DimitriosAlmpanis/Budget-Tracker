import React, {useState} from 'react'

import "./Modal.css"
import { createCategory } from '../services/CategoryService';

function ModalCategories(props) {

    const [name, setName] = useState('')
    const [categoryNameError, setCategoryNameError] = useState('')
    
    function addCategory(e) {
        e.preventDefault();

        if (validateForm()) {
            const category = {name};
            console.log("Creating category")
            createCategory(category).then((response) => {
                console.log(response.data);
            }).catch(error => {
                console.error(error);
            })
            props.closeCategoryModal(false);
        }
    }

    function validateForm() {
        let valid = true;
        if (name.trim()) {
            setCategoryNameError('')
        } else {
            setCategoryNameError('Category is required.')
            valid = false;
        }
        return valid;
    }

    return (
        <div className='modal-background'>
            <div className='modal-container'>
                <div className='close-btn'>
                    <button id='x-btn' onClick={() => props.closeCategoryModal(false)}>╳</button>
                </div>
                <div className='modal-title-container'>
                    <h2 className='modal-title'>Add Category</h2>
                </div>
                <div className='modal-body'>
                    <form>
                        <div id='category-name-container'>
                            <label htmlFor='category-field'
                            id="category-label"
                            className='required-label'>Category</label>
                            <input
                            type="text"
                            name='category-field'
                            id = 'category-field'
                            className={`category-field ${categoryNameError ? 'required-field':''}`}
                            onChange={(e) => setName(e.target.value)}
                            />
                            <div className='category-error'>
                                {categoryNameError}
                            </div>
                        </div>
                        <div className='footer-buttons'>
                            <button
                            className='modal-buttons'
                            id='footer-btn-submit'
                            onClick={addCategory}
                            >Submit</button>
                        </div>
                    </form> 
                </div>
            </div>
        </div>
    )
}
export default ModalCategories