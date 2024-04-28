import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'

import Header from './components/Header'
import Transactions from './components/Transactions'

function App() {
  const [count, setCount] = useState(0)

  return (
    <div className='app'>
      <Header/>
      <Transactions/>
    </div>
  )
}

export default App
