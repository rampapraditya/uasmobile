function Navbar() {
    return (
        <nav className="app-header navbar navbar-expand bg-body">
            <div className="container-fluid">
                <ul className="navbar-nav">
                    <li className="nav-item">
                        <a className="nav-link" data-lte-toggle="sidebar" href="#" role="button">
                            <i className="bi bi-list"></i>
                        </a>
                    </li>
                    <li className="nav-item d-none d-md-block">
                        <a href="./index.html" className="nav-link">
                            <i className="bi bi-grid-1x2 me-1" aria-hidden="true"></i>
                            Live preview
                        </a>
                    </li>
                    <li className="nav-item d-none d-md-block">
                        <a href="./docs/introduction.html" className="nav-link">
                            <i className="bi bi-book me-1" aria-hidden="true"></i>
                            Documentation
                        </a>
                    </li>
                </ul>

                <ul className="navbar-nav ms-auto">
                    <li className="nav-item">
                        <a className="nav-link" data-widget="navbar-search" href="#" role="button">
                            <i className="bi bi-search"></i>
                        </a>
                    </li>
                    <li className="nav-item dropdown">
                        <a className="nav-link" data-bs-toggle="dropdown" href="#">
                            <i className="bi bi-chat-text"></i>
                            <span className="navbar-badge badge text-bg-danger">3</span>
                        </a>
                        <div className="dropdown-menu dropdown-menu-lg dropdown-menu-end">
                            <a href="#" className="dropdown-item">
                                <div className="d-flex">
                                    <div className="flex-shrink-0">
                                        <img src="/assets/img/user1-128x128.jpg" alt="User Avatar" className="img-size-50 rounded-circle me-3"/>
                                    </div>
                                    <div className="flex-grow-1">
                                        <h3 className="dropdown-item-title">
                                            Brad Diesel
                                            <span className="float-end fs-7 text-danger">
                                                <i className="bi bi-star-fill"></i>
                                            </span>
                                        </h3>
                                        <p className="fs-7">Call me whenever you can...</p>
                                        <p className="fs-7 text-secondary">
                                            <i className="bi bi-clock-fill me-1"></i> 4 Hours Ago
                                        </p>
                                    </div>
                                </div>
                            </a>
                            <div className="dropdown-divider"></div>
                            <a href="#" className="dropdown-item">
                                <div className="d-flex">
                                    <div className="flex-shrink-0">
                                        <img src="/assets/img/user8-128x128.jpg" alt="User Avatar" className="img-size-50 rounded-circle me-3" />
                                    </div>
                                    <div className="flex-grow-1">
                                        <h3 className="dropdown-item-title">
                                            John Pierce
                                            <span className="float-end fs-7 text-secondary">
                                                <i className="bi bi-star-fill"></i>
                                            </span>
                                        </h3>
                                        <p className="fs-7">I got your message bro</p>
                                        <p className="fs-7 text-secondary">
                                            <i className="bi bi-clock-fill me-1"></i> 4 Hours Ago
                                        </p>
                                    </div>
                                </div>

                            </a>
                            <div className="dropdown-divider"></div>
                            <a href="#" className="dropdown-item">
                                <div className="d-flex">
                                    <div className="flex-shrink-0">
                                        <img src="/assets/img/user3-128x128.jpg" alt="User Avatar" className="img-size-50 rounded-circle me-3" />
                                    </div>
                                    <div className="flex-grow-1">
                                        <h3 className="dropdown-item-title">
                                            Nora Silvester
                                            <span className="float-end fs-7 text-warning">
                                                <i className="bi bi-star-fill"></i>
                                            </span>
                                        </h3>
                                        <p className="fs-7">The subject goes here</p>
                                        <p className="fs-7 text-secondary">
                                            <i className="bi bi-clock-fill me-1"></i> 4 Hours Ago
                                        </p>
                                    </div>
                                </div>
                            </a>
                            <div className="dropdown-divider"></div>
                            <a href="#" className="dropdown-item dropdown-footer">See All Messages</a>
                        </div>
                    </li>
                    <li className="nav-item dropdown">
                        <a className="nav-link" data-bs-toggle="dropdown" href="#">
                            <i className="bi bi-bell-fill"></i>
                            <span className="navbar-badge badge text-bg-warning">15</span>
                        </a>
                        <div className="dropdown-menu dropdown-menu-lg dropdown-menu-end">
                            <span className="dropdown-item dropdown-header">15 Notifications</span>
                            <div className="dropdown-divider"></div>
                            <a href="#" className="dropdown-item">
                                <i className="bi bi-envelope me-2"></i> 4 new messages
                                <span className="float-end text-secondary fs-7">3 mins</span>
                            </a>
                            <div className="dropdown-divider"></div>
                            <a href="#" className="dropdown-item">
                                <i className="bi bi-people-fill me-2"></i> 8 friend requests
                                <span className="float-end text-secondary fs-7">12 hours</span>
                            </a>
                            <div className="dropdown-divider"></div>
                            <a href="#" className="dropdown-item">
                                <i className="bi bi-file-earmark-fill me-2"></i> 3 new reports
                                <span className="float-end text-secondary fs-7">2 days</span>
                            </a>
                            <div className="dropdown-divider"></div>
                            <a href="#" className="dropdown-item dropdown-footer"> See All Notifications </a>
                        </div>
                    </li>
                    <li className="nav-item">
                        <a className="nav-link" href="#" data-lte-toggle="fullscreen">
                            <i data-lte-icon="maximize" className="bi bi-arrows-fullscreen"></i>
                            <i data-lte-icon="minimize" className="bi bi-fullscreen-exit d-none"></i>
                        </a>
                    </li>
                    <li className="nav-item dropdown">
                        <a className="nav-link" href="#" id="bd-theme" aria-label="Toggle color scheme" data-bs-toggle="dropdown" aria-expanded="false">
                            <i className="bi bi-sun-fill" data-lte-theme-icon="light"></i>
                            <i className="bi bi-moon-fill d-none" data-lte-theme-icon="dark"></i>
                            <i className="bi bi-circle-half d-none" data-lte-theme-icon="auto"></i>
                        </a>
                        <ul className="dropdown-menu dropdown-menu-end" aria-labelledby="bd-theme" style={{ "--bs-dropdown-min-width": "8rem" }}>
                            <li>
                                <button type="button" className="dropdown-item d-flex align-items-center" data-bs-theme-value="light" aria-pressed="false">
                                    <i className="bi bi-sun-fill me-2"></i>
                                    Light
                                    <i className="bi bi-check-lg ms-auto d-none"></i>
                                </button>
                            </li>
                            <li>
                                <button type="button" className="dropdown-item d-flex align-items-center" data-bs-theme-value="dark" aria-pressed="false">
                                    <i className="bi bi-moon-fill me-2"></i>
                                    Dark
                                    <i className="bi bi-check-lg ms-auto d-none"></i>
                                </button>
                            </li>
                            <li>
                                <button type="button" className="dropdown-item d-flex align-items-center active" data-bs-theme-value="auto" aria-pressed="true">
                                    <i className="bi bi-circle-half me-2"></i>
                                    Auto
                                    <i className="bi bi-check-lg ms-auto d-none"></i>
                                </button>
                            </li>
                        </ul>
                    </li>

                    <li className="nav-item dropdown user-menu">
                        <a href="#" className="nav-link dropdown-toggle" data-bs-toggle="dropdown">
                            <img src="/assets/img/user2-160x160.jpg" className="user-image rounded-circle shadow" alt="User Image" />
                            <span className="d-none d-md-inline">Alexander Pierce</span>
                        </a>
                        <ul className="dropdown-menu dropdown-menu-lg dropdown-menu-end">
                            <li className="user-header text-bg-primary">
                                <img src="/assets/img/user2-160x160.jpg" className="rounded-circle shadow" alt="User Image" />
                                <p>
                                    Alexander Pierce - Web Developer
                                    <small>Member since Nov. 2023</small>
                                </p>
                            </li>
                            <li className="user-body">
                                <div className="row">
                                    <div className="col-4 text-center">
                                        <a href="#">Followers</a>
                                    </div>
                                    <div className="col-4 text-center">
                                        <a href="#">Sales</a>
                                    </div>
                                    <div className="col-4 text-center">
                                        <a href="#">Friends</a>
                                    </div>
                                </div>
                            </li>
                            <li className="user-footer">
                                <a href="#" className="btn btn-outline-secondary">Profile</a>
                                <a href="#" className="btn btn-outline-danger float-end">Sign out</a>
                            </li>
                        </ul>
                    </li>
                </ul>
            </div>
        </nav>
    )
}

export default Navbar