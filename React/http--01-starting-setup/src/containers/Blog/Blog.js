import React, { Component, Suspense } from 'react';
import { Route, NavLink, Switch, Redirect } from 'react-router-dom';

import Posts from './Posts/Posts';
import './Blog.css';

const NewPost = React.lazy(() => import('./NewPost/NewPost'));

class Blog extends Component {

	render() {
		return (
			<div className="Blog">
				<header>
					<nav>
						<ul>
							<li><NavLink
								to="/"
								exact
								activeClassName="my-active"
								activeStyle={{
									color: '#fa923f',
									textDecoration: 'underline'
								}}>Home</NavLink></li>
							<li><NavLink to={{
								pathname: '/new-post',
								hash: '#submit',
								search: '?quick-submit=true'
							}}>New Post</NavLink></li>
						</ul>
					</nav>
				</header>
				{/*
				<Route path="/" exact render={() => <h1>Home</h1>} />
				<Route path="/" exact render={() => <h1>Welcome</h1>} />
				*/}
				<Switch>
					<Route path="/new-post" render={() => (
						<Suspense fallback={<div>Loading..</div>}>
							<NewPost />
						</Suspense>
					)} />
					<Route path="/posts" component={Posts} />
					<Route render={() => <h1>Not Found</h1>} />
					{/* <Redirect from="/" to="/posts"/> */}
				</Switch>
			</div>
		);
	}
}

export default Blog;