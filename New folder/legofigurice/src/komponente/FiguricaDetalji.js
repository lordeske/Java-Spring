import React, { useEffect, useRef, useState } from 'react';
import { Link, useParams } from 'react-router-dom';
import { getFigurica } from '../api/figuriceServise';
import { azurirajSliku } from '../api/figuriceServise'; 

function FiguricaDetalji({ fetchObrisiFiguricu }) {
    const { id } = useParams();
    const inputReferenca = useRef();

    const [objekatFigurice, setObjekatFigurice] = useState({
        nazivFigurice: '',
        urlSlike: '',
        kategorijaFigurice: '',
        materijalFigurice: ''
    });
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState(null);

    const fetchGetFigurica = async (id) => {
        try {
            const { data } = await getFigurica(id);
            setObjekatFigurice(data);
        } catch (err) {
            console.error("Error fetching figurica:", err);
            setError("Greska prilikom dohvatanja podataka o figurici.");
        }
    };

    const izaberiSliku = () => {
        inputReferenca.current.click();
    };

    const updatePhoto = async (file) => {
        if (!file) return;
        setLoading(true);
        setError(null);

        const formData = new FormData();
        formData.append('file', file, file.name);
        formData.append('idFigurice', id);

        try {
            const { data: urlSlike } = await azurirajSliku(formData);
            setObjekatFigurice((prevState) => ({
                ...prevState,
                urlSlike: urlSlike
            }));
            setLoading(false);
        } catch (err) {
            console.error(err);
            setError("Greska prilikom ažuriranja slike.");
            setLoading(false);
        }
    };

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
                        {loading ? (
                            <p>Učitavanje...</p>
                        ) : (
                            <img src={objekatFigurice.urlSlike} alt={objekatFigurice.nazivFigurice} />
                        )}
                        <div className='profile__metadata'>
                            <p className='profile__name'>{objekatFigurice.nazivFigurice}</p>
                            <p className='profile__muted'>Ovde mozes promijeniti sliku</p>
                            <button className='btn' onClick={izaberiSliku}>Promijeni sliku</button>
                        </div>
                    </div>
                    <div className='profile__settings'>
                    <form className="form">
                            <div className="user-details">
                                <div className="input-box">
                                    <span className="details">Naziv</span>
                                    <input type="text" value={objekatFigurice.nazivFigurice}  name="name" required />
                                </div>
                                <div className="input-box">
                                    <span className="details">Materijal Figurice</span>
                                    <input type="text" value={objekatFigurice.materijalFigurice}  name="materijal" required />
                                </div>
                                <div className="input-box">
                                    <span className="details">Kategorija</span>
                                    <input type="text" value={objekatFigurice.kategorijaFigurice}  name="kategorija" required />
                                </div>
                               
                            </div>
                            <div className="form_footer">
                                <button type="submit" className="btn">Save</button>
                            </div>
                        </form>
                    </div>
                    <button onClick={() => fetchObrisiFiguricu(id)}>Obrisi</button>
                </div>
                {error && <p className="error">{error}</p>}
            </div>

            <form style={{ display: "none" }}>
                <input type='file' ref={inputReferenca} onChange={(e) => updatePhoto(e.target.files[0])} name='file' accept='image/*' />
            </form>
        </>
    );
}

export default FiguricaDetalji;
