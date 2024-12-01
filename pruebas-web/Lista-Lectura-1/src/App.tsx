import { SetStateAction, useState } from 'react'
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

  // Genero para filtro
  const [selectedGenre, setSelectedGenre] = useState("Todos")

  // Mostrar los libros en la lista principal
  const showBooksAvailable = () => {
    return (
      <div className="image-container">
        {lib.library.map((item: { book: Book }) => {
          const book = item.book;
          if(selectedGenre === 'Todos' || selectedGenre === book.genre)
          return (
            <div key={book.ISBN} className='book-card'>
              <h4>{book.title}</h4>
              <img src={book.cover} alt={book.title}/>
              <button onClick={() => addBookToRead(book)}>Añadir a la lista de Lectura</button>
            </div>
          )
          return <></>
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
    setNumberOfReadings(numberOfReadings + 1);
    setNumberOfBooks(numberOfBooks - 1)
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
    setNumberOfReadings(numberOfReadings - 1)
    setNumberOfBooks(numberOfBooks + 1)
  }

  // Cuando nos digan el genero para filtrar, lo cambiamos
  const handleGenreChange = (event: { target: { value: SetStateAction<string> } }) => {
    setSelectedGenre(event.target.value)
  }

  return (
    <>
     <main>
      <h1>Primera Prueba Técnica: Lista de Lectura</h1>
      <div className='ListsContainer'>
        <div className="books-available">
          <h1>Tenemos disponibles {numberOfBooks} libros</h1>
          <h2>Tienes {numberOfReadings} en tu lista de lectura</h2>
          <div style={{display: 'flex', alignItems: 'center', gap: '5px'}}>
            <h2 style={{margin: 0, marginRight: '10px'}}>Filtrar por Género:</h2>
            <select value={selectedGenre} onChange={handleGenreChange} style={{height: "30px", minWidth: '150px', alignSelf: 'center'}}>
              <option value="Todos">Todos</option>
              <option value="Fantasía">Fantasía</option>
              <option value="Ciencia ficción">Ciencia Ficción</option>
              <option value="Zombies">Zombies</option>
              <option value="Romance">Romance</option>
              <option value="Terror">Terror</option>
            </select>
          </div>
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
