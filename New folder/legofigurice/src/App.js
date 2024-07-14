import './App.css';
import Header from './komponente/Header';
import FiguricaLista from './komponente/FiguricaLista';
import { useEffect, useState } from 'react';
import { sveFigurice } from './api/figuriceServise';

function App() {

  //use statovi
  const [data, setData] = useState({});
  const [trenutnaStrana, setTrenutnaStrana] = useState(0);

  const dobijSveFigurice = async (strana=0, velicina=10) =>    ///Dobijanje svih figurica
  {
    try
    {
      setTrenutnaStrana(strana);
      const {data} =  await sveFigurice(strana, velicina);
      setData(data);
      console.log(data);
    }
    catch (err)
    {
        console.log(err)
    }
  }


  useEffect(()=> 
  {
    dobijSveFigurice();
    
  },[])

  return (
    <div>
        <Header ></Header>
        <FiguricaLista ></FiguricaLista>
    </div>
  );
}

export default App;
