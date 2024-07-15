import React, { useEffect, useState } from 'react'
import { Link, useParams } from 'react-router-dom'
import { getFigurica } from '../api/figuriceServise'

function FiguricaDetalji({azurirajFiguricu, updateSliku}) {
  
    const [objekatFigurice, setObjekatFigurice] = useState(
        {
        "nazivFigurice": "",
        "urlSlike": "",
        "kategorijaFigurice": "",
        "materijalFigurice": ""
        }
    )

    const {id} = useParams()
    console.log(id);

    const FetchgetFigurica = async (id) =>
    {
        try 
        {   
            const {data} = await getFigurica(id);
            setObjekatFigurice(data)
            console.log(data)
        }
        catch (err)
        {

        }
       

    }

    useEffect(() =>
    {
        FetchgetFigurica();

    }, [])

    return (
    <div>
        <Link to={"/figurice"} className='link'>Vrati se nazad</Link>
        <div className='profile'>
            <div className='profile_details'> <img src={data.urlSlike} alt={data.nazivFigurice}></img> </div>
        </div>
        
    </div>
  )
}

export default FiguricaDetalji
