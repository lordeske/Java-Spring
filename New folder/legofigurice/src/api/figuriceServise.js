import axios from "axios";

const API_URL = "http://localhost/8080/figurice";



export async function kreirajFiguricu(figurica)   /// Kreiranje figurice
{
    return await axios.post(API_URL, figurica)
} 

export async function sveFigurice(strana = 0, velicina = 10)   /// dobijanje figurica, ne mora biti ovaj arg vec imamo u bekendu
{
    return await axios.get(`${API_URL}?strana=${strana}&velicina=${velicina}`)
} 

export async function gerFigurica(idFigurice)   /// dobijanje jedne figurice
{
    return await axios.get(`${API_URL}/${idFigurice}`)
} 

export async function kreirajFiguricu(figurica)   /// Kreiranje figurice
{
    return await axios.post(API_URL, figurica)
} 

export async function kreirajFiguricu(figurica)   /// Kreiranje figurice
{
    return await axios.post(API_URL, figurica)
} 