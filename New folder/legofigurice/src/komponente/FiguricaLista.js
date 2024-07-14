import React from 'react'
import Figurica from './Figurica'

const FiguricaLista = ({data, trenutnaStrana, dobijSveFigurice}) => {
  return (
    <main className='main'>
        {data?.content?.length == 0 && <div>Nema figurica, mozete dodati novu</div>}

        <ul className='contact__list'>
         {data?.content?.length > 0 && data.content.map((figurica) => <Figurica figurica ={figurica} key={figurica.idFigurice}></Figurica>)}
        </ul>



        {data?.content?.length > 0 && data?.totalPages > 1 && 
            <div className='pagination'>
                <a onClick={()=>dobijSveFigurice(trenutnaStrana - 1)} className={0 == trenutnaStrana ? "disabled" : ""} >
                    &laquo;
                </a>  
                {data &&  [...Array(data.totalPages).keys()].map((strana, ideks) => <a onClick={() => dobijSveFigurice(strana)}  className={trenutnaStrana == strana ? "active" : ""} key={strana}>{strana + 1}</a>)}
                <a onClick={()=> dobijSveFigurice(trenutnaStrana + 1 )} className={data.totalPages == trenutnaStrana + 1 ? "disabled" : ""}> 
                    &raquo;
                </a>
            </div>
        }
        
    </main>
  )
}

export default FiguricaLista
