import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'

function App() {

  const url = "https://api.thecatapi.com/v1/images/search"

  const [catImg, setCatImg] = useState(false)

  const onClick = async () => {
    const res = await fetch(url)
    const data = await res.json()
    const img = data[0].url
    const imgElement = document.createElement("img")
    imgElement.src = img
    imgElement.alt = "Gato"
    imgElement.style.width = data[0].width + "px"
    imgElement.style.height = data[0].height + "px"
    imgElement.style.objectFit = "cover"
    imgElement.style.borderRadius = "10px"
    imgElement.style.margin = "10px"
    imgElement.style.boxShadow = "0 0 10px rgba(0, 0, 0, 0.5)"
    imgElement.style.transition = "all 0.3s ease"
    imgElement.style.cursor = "pointer"
    imgElement.style.transform = "scale(1.1)"
    imgElement.style.filter = "grayscale(100%)"
    imgElement.style.objectPosition = "center"
    imgElement.style.backgroundColor = "white"
    imgElement.style.border = "2px solid black"
    imgElement.style.padding = "10px"
    imgElement.style.boxSizing = "border-box"
    imgElement.style.display = "block"
    imgElement.style.margin = "0 auto"
    imgElement.style.maxWidth = "100%"
    imgElement.style.maxHeight = "100%"
    imgElement.style.position = "relative"
    imgElement.style.zIndex = "1"
    imgElement.style.animation = "fadeIn 1s"
    imgElement.style.opacity = "0"
    imgElement.style.transition = "opacity 1s"
    imgElement.style.animation = "fadeIn 1s forwards"
    imgElement.style.opacity = "1"
    imgElement.style.filter = "grayscale(0%)"
    imgElement.style.boxShadow = "0 0 10px rgba(0, 0, 0, 0.5)"
    imgElement.style.transform = "scale(1)"
    imgElement.style.transition = "all 0.3s ease"

    setCatImg(true)
    const card = document.querySelector(".card")
    if (card) {
      card.appendChild(imgElement)
    }
  }

  return (
    <>
      <button onClick={onClick} className="btn">
        Pulsa para obtener una imagen de un gato
      </button>
      <div className="card">
        <button onClick={onClick}>

        </button>
      </div>
    </>
  )
}

export default App
