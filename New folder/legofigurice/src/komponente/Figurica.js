

import React from 'react';
import { Link } from 'react-router-dom';

const Figurica = ({ figurica }) => {
    
    const imagePath = `/img/${figurica.urlSlike}`;

    return (
        <Link to={`/figurice/${figurica.idFigurice}`} className="contact__item">
            <div className="contact__header">
                <div className="contact__image">
                    <img src={imagePath} alt={figurica.nazivFigurice} />
                </div>
                <div className="contact__details">
                    <p className="contact_name">{figurica.nazivFigurice}</p>
                </div>
            </div>
            <div className="contact__body">
                <p>{figurica.materijalFigurice}</p>
                <p>{figurica.kategorijaFigurice}</p>
            </div>
        </Link>
    );
};

export default Figurica;
