function Sidebar() {
    return (
        <aside className="app-sidebar bg-body-secondary shadow" data-bs-theme="dark">
            <div className="sidebar-brand">
                <a href="./index.html" className="brand-link">
                    <img src="/assets/img/AdminLTELogo.png" alt="AdminLTE Logo" className="brand-image opacity-75 shadow" />
                    <span className="brand-text fw-light">AdminLTE 4</span>
                </a>
            </div>
            <div className="sidebar-wrapper">
                <nav className="mt-2">
                    <div className="px-3 pb-2">
                        <a href="./docs/introduction.html" className="btn btn-sm btn-outline-light w-100 d-flex align-items-center justify-content-center gap-2">
                            <i className="bi bi-book" aria-hidden="true"></i>
                            View documentation
                        </a>
                    </div>
                    <ul className="nav sidebar-menu flex-column" data-lte-toggle="treeview" role="navigation" aria-label="Main navigation" data-accordion="false" id="navigation" >
                        <li className="nav-item menu-open">
                            <a href="#" className="nav-link active">
                                <i className="nav-icon bi bi-speedometer"></i>
                                <p>
                                    Dashboard
                                    <i className="nav-arrow bi bi-chevron-right"></i>
                                </p>
                            </a>
                            <ul className="nav nav-treeview">
                                <li className="nav-item">
                                    <a href="./index.html" className="nav-link active">
                                        <i className="nav-icon bi bi-circle"></i>
                                        <p>Dashboard v1</p>
                                    </a>
                                </li>
                                <li className="nav-item">
                                    <a href="./index2.html" className="nav-link">
                                        <i className="nav-icon bi bi-circle"></i>
                                        <p>Dashboard v2</p>
                                    </a>
                                </li>
                                <li className="nav-item">
                                    <a href="./index3.html" className="nav-link">
                                        <i className="nav-icon bi bi-circle"></i>
                                        <p>Dashboard v3</p>
                                    </a>
                                </li>
                            </ul>
                        </li>
                        <li className="nav-item">
                            <a href="./generate/theme.html" className="nav-link">
                                <i className="nav-icon bi bi-palette"></i>
                                <p>Theme Generate</p>
                            </a>
                        </li>
                        <li className="nav-item">
                            <a href="#" className="nav-link">
                                <i className="nav-icon bi bi-box-seam-fill"></i>
                                <p>
                                    Widgets
                                    <i className="nav-arrow bi bi-chevron-right"></i>
                                </p>
                            </a>
                            <ul className="nav nav-treeview">
                                <li className="nav-item">
                                    <a href="./widgets/small-box.html" className="nav-link">
                                        <i className="nav-icon bi bi-circle"></i>
                                        <p>Small Box</p>
                                    </a>
                                </li>
                                <li className="nav-item">
                                    <a href="./widgets/info-box.html" className="nav-link">
                                        <i className="nav-icon bi bi-circle"></i>
                                        <p>info Box</p>
                                    </a>
                                </li>
                                <li className="nav-item">
                                    <a href="./widgets/cards.html" className="nav-link">
                                        <i className="nav-icon bi bi-circle"></i>
                                        <p>Cards</p>
                                    </a>
                                </li>
                            </ul>
                        </li>
                        <li className="nav-item">
                            <a href="#" className="nav-link">
                                <i className="nav-icon bi bi-clipboard-fill"></i>
                                <p>
                                    Layout Options
                                    <span className="nav-badge badge text-bg-secondary me-3">7</span>
                                    <i className="nav-arrow bi bi-chevron-right"></i>
                                </p>
                            </a>
                            <ul className="nav nav-treeview">
                                <li className="nav-item">
                                    <a href="./layout/unfixed-sidebar.html" className="nav-link">
                                        <i className="nav-icon bi bi-circle"></i>
                                        <p>Default Sidebar</p>
                                    </a>
                                </li>
                                <li className="nav-item">
                                    <a href="./layout/fixed-sidebar.html" className="nav-link">
                                        <i className="nav-icon bi bi-circle"></i>
                                        <p>Fixed Sidebar</p>
                                    </a>
                                </li>
                                <li className="nav-item">
                                    <a href="./layout/fixed-header.html" className="nav-link">
                                        <i className="nav-icon bi bi-circle"></i>
                                        <p>Fixed Header</p>
                                    </a>
                                </li>
                                <li className="nav-item">
                                    <a href="./layout/fixed-footer.html" className="nav-link">
                                        <i className="nav-icon bi bi-circle"></i>
                                        <p>Fixed Footer</p>
                                    </a>
                                </li>
                                <li className="nav-item">
                                    <a href="./layout/fixed-complete.html" className="nav-link">
                                        <i className="nav-icon bi bi-circle"></i>
                                        <p>Fixed Complete</p>
                                    </a>
                                </li>
                                <li className="nav-item">
                                    <a href="./layout/layout-custom-area.html" className="nav-link">
                                        <i className="nav-icon bi bi-circle"></i>
                                        <p>Layout <small>+ Custom Area </small></p>
                                    </a>
                                </li>
                                <li className="nav-item">
                                    <a href="./layout/sidebar-mini.html" className="nav-link">
                                        <i className="nav-icon bi bi-circle"></i>
                                        <p>Sidebar Mini</p>
                                    </a>
                                </li>
                                <li className="nav-item">
                                    <a href="./layout/collapsed-sidebar.html" className="nav-link">
                                        <i className="nav-icon bi bi-circle"></i>
                                        <p>Sidebar Mini <small>+ Collapsed</small></p>
                                    </a>
                                </li>
                                <li className="nav-item">
                                    <a href="./layout/collapsed-sidebar-without-hover.html" className="nav-link">
                                        <i className="nav-icon bi bi-circle"></i>
                                        <p>Sidebar Mini <small>+ Collapsed + No Hover</small></p>
                                    </a>
                                </li>
                                <li className="nav-item">
                                    <a href="./layout/logo-switch.html" className="nav-link">
                                        <i className="nav-icon bi bi-circle"></i>
                                        <p>Sidebar Mini <small>+ Logo Switch</small></p>
                                    </a>
                                </li>
                                <li className="nav-item">
                                    <a href="./layout/layout-rtl.html" className="nav-link">
                                        <i className="nav-icon bi bi-circle"></i>
                                        <p>Layout RTL</p>
                                    </a>
                                </li>
                            </ul>
                        </li>
                        <li className="nav-item">
                            <a href="#" className="nav-link">
                                <i className="nav-icon bi bi-tree-fill"></i>
                                <p>
                                    UI Elements
                                    <i className="nav-arrow bi bi-chevron-right"></i>
                                </p>
                            </a>
                            <ul className="nav nav-treeview">
                                <li className="nav-item">
                                    <a href="./UI/general.html" className="nav-link">
                                        <i className="nav-icon bi bi-circle"></i>
                                        <p>General</p>
                                    </a>
                                </li>
                                <li className="nav-item">
                                    <a href="./UI/icons.html" className="nav-link">
                                        <i className="nav-icon bi bi-circle"></i>
                                        <p>Icons</p>
                                    </a>
                                </li>
                                <li className="nav-item">
                                    <a href="./UI/timeline.html" className="nav-link">
                                        <i className="nav-icon bi bi-circle"></i>
                                        <p>Timeline</p>
                                    </a>
                                </li>
                            </ul>
                        </li>
                        <li className="nav-item">
                            <a href="#" className="nav-link">
                                <i className="nav-icon bi bi-envelope"></i>
                                <p>
                                    Mailbox
                                    <i className="nav-arrow bi bi-chevron-right"></i>
                                </p>
                            </a>
                            <ul className="nav nav-treeview">
                                <li className="nav-item">
                                    <a href="./mailbox/inbox.html" className="nav-link">
                                        <i className="nav-icon bi bi-circle"></i>
                                        <p>Inbox</p>
                                    </a>
                                </li>
                                <li className="nav-item">
                                    <a href="./mailbox/read.html" className="nav-link">
                                        <i className="nav-icon bi bi-circle"></i>
                                        <p>Read Message</p>
                                    </a>
                                </li>
                                <li className="nav-item">
                                    <a href="./mailbox/compose.html" className="nav-link">
                                        <i className="nav-icon bi bi-circle"></i>
                                        <p>Compose</p>
                                    </a>
                                </li>
                            </ul>
                        </li>

                        <li className="nav-item">
                            <a href="#" className="nav-link">
                                <i className="nav-icon bi bi-pencil-square"></i>
                                <p>
                                    Forms
                                    <i className="nav-arrow bi bi-chevron-right"></i>
                                </p>
                            </a>
                            <ul className="nav nav-treeview">
                                <li className="nav-item">
                                    <a href="./forms/elements.html" className="nav-link">
                                        <i className="nav-icon bi bi-circle"></i>
                                        <p>Elements</p>
                                    </a>
                                </li>
                                <li className="nav-item">
                                    <a href="./forms/layout.html" className="nav-link">
                                        <i className="nav-icon bi bi-circle"></i>
                                        <p>Layout</p>
                                    </a>
                                </li>
                                <li className="nav-item">
                                    <a href="./forms/validation.html" className="nav-link">
                                        <i className="nav-icon bi bi-circle"></i>
                                        <p>Validation</p>
                                    </a>
                                </li>
                                <li className="nav-item">
                                    <a href="./forms/wizard.html" className="nav-link">
                                        <i className="nav-icon bi bi-circle"></i>
                                        <p>Wizard</p>
                                    </a>
                                </li>
                            </ul>
                        </li>
                        <li className="nav-item">
                            <a href="#" className="nav-link">
                                <i className="nav-icon bi bi-table"></i>
                                <p>
                                    Tables
                                    <i className="nav-arrow bi bi-chevron-right"></i>
                                </p>
                            </a>
                            <ul className="nav nav-treeview">
                                <li className="nav-item">
                                    <a href="./tables/simple.html" className="nav-link">
                                        <i className="nav-icon bi bi-circle"></i>
                                        <p>Simple Tables</p>
                                    </a>
                                </li>
                                <li className="nav-item">
                                    <a href="./tables/data.html" className="nav-link">
                                        <i className="nav-icon bi bi-circle"></i>
                                        <p>Data Tables</p>
                                    </a>
                                </li>
                            </ul>
                        </li>

                        <li className="nav-header">PAGES</li>
                        <li className="nav-item">
                            <a href="#" className="nav-link">
                                <i className="nav-icon bi bi-file-earmark-text"></i>
                                <p>
                                    Pages
                                    <i className="nav-arrow bi bi-chevron-right"></i>
                                </p>
                            </a>
                            <ul className="nav nav-treeview">
                                <li className="nav-item">
                                    <a href="./pages/profile.html" className="nav-link">
                                        <i className="nav-icon bi bi-circle"></i>
                                        <p>Profile</p>
                                    </a>
                                </li>
                                <li className="nav-item">
                                    <a href="./pages/settings.html" className="nav-link">
                                        <i className="nav-icon bi bi-circle"></i>
                                        <p>Settings</p>
                                    </a>
                                </li>
                                <li className="nav-item">
                                    <a href="./pages/invoice.html" className="nav-link">
                                        <i className="nav-icon bi bi-circle"></i>
                                        <p>Invoice</p>
                                    </a>
                                </li>
                                <li className="nav-item">
                                    <a href="./pages/calendar.html" className="nav-link">
                                        <i className="nav-icon bi bi-circle"></i>
                                        <p>Calendar</p>
                                    </a>
                                </li>
                                <li className="nav-item">
                                    <a href="./pages/kanban.html" className="nav-link">
                                        <i className="nav-icon bi bi-circle"></i>
                                        <p>Kanban</p>
                                    </a>
                                </li>
                                <li className="nav-item">
                                    <a href="./pages/chat.html" className="nav-link">
                                        <i className="nav-icon bi bi-circle"></i>
                                        <p>Chat</p>
                                    </a>
                                </li>
                                <li className="nav-item">
                                    <a href="./pages/file-manager.html" className="nav-link">
                                        <i className="nav-icon bi bi-circle"></i>
                                        <p>File Manager</p>
                                    </a>
                                </li>
                                <li className="nav-item">
                                    <a href="./pages/projects.html" className="nav-link">
                                        <i className="nav-icon bi bi-circle"></i>
                                        <p>Projects</p>
                                    </a>
                                </li>
                                <li className="nav-item">
                                    <a href="./pages/pricing.html" className="nav-link">
                                        <i className="nav-icon bi bi-circle"></i>
                                        <p>Pricing</p>
                                    </a>
                                </li>
                                <li className="nav-item">
                                    <a href="./pages/faq.html" className="nav-link">
                                        <i className="nav-icon bi bi-circle"></i>
                                        <p>FAQ</p>
                                    </a>
                                </li>
                                <li className="nav-item">
                                    <a href="#" className="nav-link">
                                        <i className="nav-icon bi bi-circle"></i>
                                        <p>
                                            Error
                                            <i className="nav-arrow bi bi-chevron-right"></i>
                                        </p>
                                    </a>
                                    <ul className="nav nav-treeview">
                                        <li className="nav-item">
                                            <a href="./pages/404.html" className="nav-link">
                                                <i className="nav-icon bi bi-circle"></i>
                                                <p>404</p>
                                            </a>
                                        </li>
                                        <li className="nav-item">
                                            <a href="./pages/500.html" className="nav-link">
                                                <i className="nav-icon bi bi-circle"></i>
                                                <p>500</p>
                                            </a>
                                        </li>
                                        <li className="nav-item">
                                            <a href="./pages/maintenance.html" className="nav-link">
                                                <i className="nav-icon bi bi-circle"></i>
                                                <p>Maintenance</p>
                                            </a>
                                        </li>
                                    </ul>
                                </li>
                            </ul>
                        </li>

                        <li className="nav-header">EXAMPLES</li>
                        <li className="nav-item">
                            <a href="#" className="nav-link">
                                <i className="nav-icon bi bi-box-arrow-in-right"></i>
                                <p>
                                    Auth
                                    <i className="nav-arrow bi bi-chevron-right"></i>
                                </p>
                            </a>
                            <ul className="nav nav-treeview">
                                <li className="nav-item">
                                    <a href="#" className="nav-link">
                                        <i className="nav-icon bi bi-box-arrow-in-right"></i>
                                        <p>
                                            Version 1
                                            <i className="nav-arrow bi bi-chevron-right"></i>
                                        </p>
                                    </a>
                                    <ul className="nav nav-treeview">
                                        <li className="nav-item">
                                            <a href="./examples/login.html" className="nav-link">
                                                <i className="nav-icon bi bi-circle"></i>
                                                <p>Login</p>
                                            </a>
                                        </li>
                                        <li className="nav-item">
                                            <a href="./examples/register.html" className="nav-link">
                                                <i className="nav-icon bi bi-circle"></i>
                                                <p>Register</p>
                                            </a>
                                        </li>
                                    </ul>
                                </li>
                                <li className="nav-item">
                                    <a href="#" className="nav-link">
                                        <i className="nav-icon bi bi-box-arrow-in-right"></i>
                                        <p>
                                            Version 2
                                            <i className="nav-arrow bi bi-chevron-right"></i>
                                        </p>
                                    </a>
                                    <ul className="nav nav-treeview">
                                        <li className="nav-item">
                                            <a href="./examples/login-v2.html" className="nav-link">
                                                <i className="nav-icon bi bi-circle"></i>
                                                <p>Login</p>
                                            </a>
                                        </li>
                                        <li className="nav-item">
                                            <a href="./examples/register-v2.html" className="nav-link">
                                                <i className="nav-icon bi bi-circle"></i>
                                                <p>Register</p>
                                            </a>
                                        </li>
                                    </ul>
                                </li>
                                <li className="nav-item">
                                    <a href="./examples/lockscreen.html" className="nav-link">
                                        <i className="nav-icon bi bi-circle"></i>
                                        <p>Lockscreen</p>
                                    </a>
                                </li>
                            </ul>
                        </li>

                        <li className="nav-header">MULTI LEVEL EXAMPLE</li>
                        <li className="nav-item">
                            <a href="#" className="nav-link">
                                <i className="nav-icon bi bi-circle-fill"></i>
                                <p>Level 1</p>
                            </a>
                        </li>
                        <li className="nav-item">
                            <a href="#" className="nav-link">
                                <i className="nav-icon bi bi-circle-fill"></i>
                                <p>
                                    Level 1
                                    <i className="nav-arrow bi bi-chevron-right"></i>
                                </p>
                            </a>
                            <ul className="nav nav-treeview">
                                <li className="nav-item">
                                    <a href="#" className="nav-link">
                                        <i className="nav-icon bi bi-circle"></i>
                                        <p>Level 2</p>
                                    </a>
                                </li>
                                <li className="nav-item">
                                    <a href="#" className="nav-link">
                                        <i className="nav-icon bi bi-circle"></i>
                                        <p>
                                            Level 2
                                            <i className="nav-arrow bi bi-chevron-right"></i>
                                        </p>
                                    </a>
                                    <ul className="nav nav-treeview">
                                        <li className="nav-item">
                                            <a href="#" className="nav-link">
                                                <i className="nav-icon bi bi-record-circle-fill"></i>
                                                <p>Level 3</p>
                                            </a>
                                        </li>
                                        <li className="nav-item">
                                            <a href="#" className="nav-link">
                                                <i className="nav-icon bi bi-record-circle-fill"></i>
                                                <p>Level 3</p>
                                            </a>
                                        </li>
                                        <li className="nav-item">
                                            <a href="#" className="nav-link">
                                                <i className="nav-icon bi bi-record-circle-fill"></i>
                                                <p>Level 3</p>
                                            </a>
                                        </li>
                                    </ul>
                                </li>
                                <li className="nav-item">
                                    <a href="#" className="nav-link">
                                        <i className="nav-icon bi bi-circle"></i>
                                        <p>Level 2</p>
                                    </a>
                                </li>
                            </ul>
                        </li>
                        <li className="nav-item">
                            <a href="#" className="nav-link">
                                <i className="nav-icon bi bi-circle-fill"></i>
                                <p>Level 1</p>
                            </a>
                        </li>

                        <li className="nav-header">LABELS</li>
                        <li className="nav-item">
                            <a href="#" className="nav-link">
                                <i className="nav-icon bi bi-circle text-danger"></i>
                                <p className="text">Important</p>
                            </a>
                        </li>
                        <li className="nav-item">
                            <a href="#" className="nav-link">
                                <i className="nav-icon bi bi-circle text-warning"></i>
                                <p>Warning</p>
                            </a>
                        </li>
                        <li className="nav-item">
                            <a href="#" className="nav-link">
                                <i className="nav-icon bi bi-circle text-info"></i>
                                <p>Informational</p>
                            </a>
                        </li>
                    </ul>
                </nav>
            </div>
        </aside>
    )
}

export default Sidebar