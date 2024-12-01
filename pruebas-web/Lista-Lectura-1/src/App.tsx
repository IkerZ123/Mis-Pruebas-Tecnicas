import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'

import books from './books.json' assert { type: 'json' }

interface Book{
  title: string;
  pages: number;
  genre: string;
  cover: string;
  synopsis: string;
  year: number;
  ISBN: string
  author: Author;
}

interface Author {
  name: string;
  otherBooks: string[];
}

function App() {

  // Coger todos los datos de todos los libros
  const lib = JSON.parse(JSON.stringify(books))

  // Nuestra Lista de lectura: guardamos los ISBN como identicadores
  // Importante hacerlo con array de Book!
  const [readings, setReadings] = useState<Book[]>([])

  // Cuantos libros hay disponibles?
  // Lo asignamos desde el principio
  const [numberOfBooks, setNumberOfBooks] = useState(lib.library.length)

  // Cuantos libros hay en mi lista de lectura?
  const [numberOfReadings, setNumberOfReadings] = useState(0)

  // Mostrar los libros en la lista principal
  const showBooksAvailable = () => {
    return (
      <div className="image-container">
        {lib.library.map((item: { book: Book }) => {
          const book = item.book;
          return (
            <div key={book.ISBN} className='book-card'>
              <h4>{book.title}</h4>
              <img src={book.cover} alt={book.title}/>
              <button onClick={() => addBookToRead(book)}>Añadir a la lista de Lectura</button>
            </div>
          )
        })}
      </div>
    )
  }

  // Añadir el libro que hemos seleccionado a nuestra lista de lectura
  // Lo metemos como objeto y toda su informacion por si la necesitaramos en algun momento
  const addBookToRead = (book: Book) => {
    if(!readings.some(item => item.ISBN === book.ISBN)){
      setReadings(previous => [...previous, {
        title: book.title,
        pages: book.pages,
        genre: book.genre,
        cover: book.cover,
        synopsis: book.synopsis,
        year: book.year,
        ISBN: book.ISBN,
        author: book.author}])
    }
  }

  // Mostrar nuestra lista de lectura
  const showReadings = () => {
    return (
      <div className='readings-container'>
        {readings.map(book => {
          return (
            <div key={book.ISBN}>
              <img src={book.cover} alt={book.title}/>
              <button onClick={() => deleteBookToRead(book)}>Quitar de la lista de Lectura</button>
            </div>
          )
        })}
      </div>
    )
  }

  // Quitar un libro que nos hayan dicho
  const deleteBookToRead = (book: Book) => {
    setReadings(readings.filter(reading => reading.ISBN !== book.ISBN))
  }

  return (
    <>
     <main>
      <h1>Primera Prueba Técnica: Lista de Lectura</h1>
      <div className='ListsContainer'>
        <div className="books-available">
          <h1>Tenemos disponibles {numberOfBooks} libros</h1>
          {showBooksAvailable()}
        </div>
        <div style={{flex: 1}}>
          <h1>Tu lista de Lectura</h1>
          {readings.length !== 0 ? showReadings() : <h2>No tienes nada por leer</h2>}
        </div>
      </div>
      </main>
    </>
  )
}

export default App
