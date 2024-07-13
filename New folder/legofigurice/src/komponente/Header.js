import React from 'react'

const Header = ({toggleModel, brojFigurica}) => {
  return (
    <div>
      <header className='header'>
        <div className='container'>
            <h3>{brojFigurica ? `Broj figurica na stanju je: ${brojFigurica}`  : "Nema figurica na stanju"}</h3>
            <button onClick={() => toggleModel(true)} className='btn'> <i className='bi bi-plus-quare '>Dodaj novu figuricu</i></button>
        </div>
      </header>
    </div>
  )
}

export default Header
