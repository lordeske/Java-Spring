import React from 'react'
import { Link } from 'react-router-dom'

const Figurica = ({figurica }) => {
  return (
    <Link  to={`/figurice/${figurica.idFigurice}`}
     className='contact_item'>
        <div className='contact_header'>
            <div className='contact__image'>
                <img src={figurica.url_slike} alt={figurica.nazivFigurice}></img>

            </div>
            <div className='contact__details'>  
                <p className='contact_name'>{figurica.nazivFigurice}</p>
                <p className='contact_title'>{figurica.materijalFigurice}</p>
            </div>
        </div>
        <div className='contact_body'>
            <div>
                <p></p>
                <p></p>  
            </div>

        </div>
    </Link>
  )
}

export default Figurica
