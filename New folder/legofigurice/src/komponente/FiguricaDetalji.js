import React, { useEffect, useState } from 'react';
import { Link, useParams } from 'react-router-dom';
import { getFigurica } from '../api/figuriceServise';

function FiguricaDetalji({ azurirajFiguricu, updateSliku }) {
    const [objekatFigurice, setObjekatFigurice] = useState({
        nazivFigurice: '',
        urlSlike: '',
        kategorijaFigurice: '',
        materijalFigurice: ''
    });

    const { id } = useParams();

    const fetchGetFigurica = async (id) => {
        try {
            const { data } = await getFigurica(id);
            setObjekatFigurice(data);
            console.log(data);
            console.log(id);
        } catch (err) {
            console.log(err);
        }
    };

    useEffect(() => {
        if (id) {
            fetchGetFigurica(id);
        }
    }, [id]);

    return (
        <div>
            <Link to="/figurice" className='link'>Vrati se nazad</Link>
            <div className='profile'>
                <div className='profile_details'>
                    <img src={objekatFigurice.urlSlike} alt={objekatFigurice.nazivFigurice} />
                    <div className='profile__metadata'>
                        <p className='profile__name'>{objekatFigurice.nazivFigurice}</p>
                        <p className='profile__muted'>Ovde mozes promijeniti sliku</p>
                        <button className='btn'>Promijeni sliku</button>
                    </div>
                </div>
                <div className='profile__settings'>
                    <p>Kategorija: {objekatFigurice.kategorijaFigurice}</p>
                    <p>Materijal: {objekatFigurice.materijalFigurice}</p>
                </div>
            </div>
        </div>
    );
}

export default FiguricaDetalji;
