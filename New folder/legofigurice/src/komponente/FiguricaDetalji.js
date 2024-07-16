import React, { useEffect, useRef, useState } from 'react';
import { Link, useParams } from 'react-router-dom';
import { getFigurica } from '../api/figuriceServise';

function FiguricaDetalji({ azurirajFotografiju, fetchObrisiFiguricu }) {

    const { id } = useParams();
    const inputReferenca = useRef();



    const [objekatFigurice, setObjekatFigurice] = useState({
        nazivFigurice: '',
        urlSlike: '',
        kategorijaFigurice: '',
        materijalFigurice: ''
    });



    const fetchGetFigurica = async (id) => {
        try {
            const { data } = await getFigurica(id);
            setObjekatFigurice(data);

        } catch (err) {
            console.log(err);
        }
    };

    const izaberiSliku = async () => {
        inputReferenca.current.click();
    }

    const promijeniSliku = async (fajl) => {

        try {
            const formData = new FormData();
            formData.append('file', fajl, fajl.name);
            formData.append("idFigurice", data.idFigurice);
            const { data } = await azurirajFotografiju(formData);
            setObjekatFigurice(data);
            console.log(data)
        }
        catch (err) {
            console.log(err)
        }



    }



    useEffect(() => {
        if (id) {
            fetchGetFigurica(id);
        }
    }, [id]);

    return (
        <>
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
                    <button onClick={() => fetchObrisiFiguricu(id)}>Obrisi</button>
                    <button onClick={izaberiSliku}>Upladuj drugu sliku</button>
                </div>
            </div>

            <form style={{ display: "none" }}>
                <input type='file' ref={inputReferenca} onCanPlay={(e) => promijeniSliku(e.target.file[0])} name='file' accept='image/*'></input>
            </form>
        </>
    );
}

export default FiguricaDetalji;
