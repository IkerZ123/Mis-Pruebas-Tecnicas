import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'

import books from './books.json' assert { type: 'json' }

function App() {

  // Coger todos los datos de todos los libros
  const lib = JSON.parse(JSON.stringify(books))

  // Mostrar los libros en la lista principal
  const showBooksAvailable = () => {
    return lib.library.map((item: { book: { title: string, cover: string }}) => {
      const book = item.book
      return <div>
        <h2>{book.title}</h2>
        <img src={book.cover} alt="Titulo del libro"></img>
        <button onClick={addBookToRead}>Añadir a la Lista de Lectura</button>
      </div>
    })
  }

  const addBookToRead = () => {

  }

  return (
    <>
     <main>
      <h1>Primera Prueba Técnica: Lista de Lectura</h1>
      {showBooksAvailable()}
     </main>
    </>
  )
}

export default App
