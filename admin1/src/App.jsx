import { useEffect } from 'react'
import { OverlayScrollbars } from 'overlayscrollbars'

import Navbar from './components/Navbar.jsx'
import Sidebar from './components/Sidebar.jsx'
import Dashboard from './pages/Dashboard.jsx'
import Footer from './components/Footer.jsx'

function App() {

  useEffect(() => {
    // === 1. FUNGSI UNTUK OVERLAY SCROLLBARS ===
    const sidebarWrapper = document.querySelector('.sidebar-wrapper');
    const isMobile = window.innerWidth <= 992;

    if (sidebarWrapper && !isMobile) {
      OverlayScrollbars(sidebarWrapper, {
        scrollbars: {
          theme: 'os-theme-light',
          autoHide: 'leave',
          clickScroll: true,
        },
      });
    }

    // === 2. FUNGSI UNTUK MENU & SIDEBAR ADMINLTE V4 ===
    const initAdminLTE = async () => {
      try {
        const adminlteModule = await import('admin-lte/dist/js/adminlte.js');
        const AdminLTE = adminlteModule.default || adminlteModule;

        // Inisialisasi Menu Dropdown (Treeview) - Cukup pakai "new" saja, hapus .init()
        const treeElements = document.querySelectorAll('[data-lte-toggle="treeview"]');
        treeElements.forEach((el) => {
          if (AdminLTE && AdminLTE.Treeview) {
            new AdminLTE.Treeview(el); // <-- .init() Dihapus di sini
          }
        });

        // Inisialisasi Tombol Buka/Tutup Sidebar (PushMenu) - Cukup pakai "new" saja
        const pushMenuElements = document.querySelectorAll('[data-lte-toggle="sidebar"]');
        pushMenuElements.forEach((el) => {
          if (AdminLTE && AdminLTE.PushMenu) {
            new AdminLTE.PushMenu(el); // <-- .init() Dihapus di sini
          }
        });

      } catch (error) {
        console.error("Gagal memandirikan JavaScript AdminLTE:", error);
      }
    };

    // Jalankan fungsi AdminLTE setelah layout siap
    initAdminLTE();

  }, []); // Tetap dikunci dengan array kosong



  return (
    <div className="app-wrapper">
      <Navbar />
      <Sidebar />
      <Dashboard />
      <Footer />
    </div>
  )
}

export default App
