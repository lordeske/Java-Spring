import axios from "axios";

const API_URL = "http://localhost:8080/figurice";



export async function kreirajFiguricu(figurica)   /// Kreiranje figurice
{
    return await axios.post(API_URL, figurica)
} 

export async function sveFigurice(page = 0, size = 10)   /// dobijanje figurica, ne mora biti ovaj arg vec imamo u bekendu
{
    return await axios.get(`${API_URL}?page=${page}&size=${size}`)
} 

export async function getFigurica(idFigurice)   /// dobijanje jedne figurice
{
    return await axios.get(`${API_URL}/${idFigurice}`)
} 

export async function azurirajFiguricu(figurica)   /// Azuriranje figurice
{
    return await axios.post(API_URL, figurica)
} 

export async function azurirajSliku(formData)   /// Azuriranje slike
{
    return await axios.put(`${API_URL}/slika`, formData)
} 


export async function obrisiFiguricu(idFigurice)   /// Obrisi figuricu
{
    return await axios.delete(`${API_URL}/${idFigurice}`)
} 
