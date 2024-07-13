import './App.css';
import Header from './komponente/Header';
import FiguricaLista from './komponente/FiguricaLista';

function App() {

  const broj = 2;

  const data = [{ 
    "idFigurice" : 1,
    "nazivFigurice" :  "marko",
    "materijalFigurice" : "sos",
    "kategorijaFigurice" : "nesto"
  }]


  return (
    <div>
        <Header brojFigurica={broj}></Header>
        <FiguricaLista data={data}></FiguricaLista>
    </div>
  );
}

export default App;
