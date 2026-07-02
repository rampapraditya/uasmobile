import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'

import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap/dist/js/bootstrap.bundle.min.js'
import 'bootstrap-icons/font/bootstrap-icons.css'

import "@fontsource/source-sans-3";

import 'overlayscrollbars/overlayscrollbars.css';

import 'admin-lte/dist/css/adminlte.css'
// import 'admin-lte/dist/js/adminlte.js'

import App from './App.jsx'

createRoot(document.getElementById('root')).render(
  <StrictMode>
    <App />
  </StrictMode>,
)
