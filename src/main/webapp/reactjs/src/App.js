import React from 'react';
import './App.css';

import {Container, Row, Col} from 'react-bootstrap';
import {BrowserRouter as Router, Switch, Route} from 'react-router-dom';

import NavigationBar from './components/NavigationBar';
import Welcome from './components/Welcome';
import Book from './components/Book/Book';
import BookList from './components/Book/BookList';
import LibraryList from './components/Library/LibraryList';
import UserList from './components/User/UserList';
import Footer from './components/Footer';

export default function App() {

  const heading = "Welcome to Books Library";
  const quote = "Good friends, good books, and a sleepy conscience: this is the ideal life.";
  const footer = "Mark Twain";

  return (
    <Router>
        <NavigationBar/>
        <Container>
            <Row>
                <Col lg={12} className={"margin-top"}>
                    <Switch>
                        <Route path="/" exact component={() => <Welcome heading={heading} quote={quote} footer={footer}/>}/>
                        <Route path="/add" exact component={Book}/>
                        <Route path="/books/edit/:id" exact component={Book}/>
                        <Route path="/libraries" exact component={LibraryList}/>
                        <Route path="/books/:id" exact component={BookList}/>
                        <Route path="/users" exact component={UserList}/>
                    </Switch>
                </Col>
            </Row>
        </Container>
        <Footer/>
    </Router>
  );
}
