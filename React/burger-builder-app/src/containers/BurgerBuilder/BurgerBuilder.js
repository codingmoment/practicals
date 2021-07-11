import React, { Component } from "react";

import Aux from '../../hoc/Auxi/Auxi';
import Burger from '../../components/Burger/Burger';
import BuildControls from '../../components/Burger/BuildControls/BuildControls';
import Modal from '../../components/UI/Modal/Modal';
import OrderSummary from '../../components/Burger/OrderSummary/OrderSummary';
import Spinner from '../../components/UI/Spinner/Spinner';
import withErrorHandler from '../../hoc/withErrorHandler/withErrorHandler';
import axios from '../../axios-orders';

const INGREDIENT_PRICE = {
  salad: 0.5,
  cheese: 0.4,
  meat: 1.3,
  bacon: 0.7
};

class BurgerBuilder extends Component {
  state = {
    ingredients: null,
    price: 4,
    purchasable: false,
    purchasing: false,
    loading: false,
    error: false
  }

  componentDidMount() {
    axios.get('ingredients.json')
      .then(response => {
        this.setState({
          ingredients: response.data
        });
      })
      .catch(error => {
        this.setState({
          error: true
        });
      });
  }

  purchasable = (ingredients) => {
    const totalQuantity = Object.keys(ingredients)
      .map(ingredientKey => {
        return ingredients[ingredientKey];
      })
      .reduce((total, currentQuantity) => {
        return total + currentQuantity;
      }, 0);
    this.setState({ purchasable: totalQuantity > 0 });
  }

  addIngredientHandler = (type) => {
    const currentCount = this.state.ingredients[type];
    const updatedIngredients = {
      ...this.state.ingredients
    };
    updatedIngredients[type] = currentCount + 1;
    const updatedPrice = this.state.price + INGREDIENT_PRICE[type];
    this.setState({ ingredients: updatedIngredients, price: updatedPrice });
    this.purchasable(updatedIngredients);
  }

  removeIngredientHandler = (type) => {
    const currentCount = this.state.ingredients[type];
    if (currentCount <= 0) {
      return;
    }
    const updatedIngredients = {
      ...this.state.ingredients
    };
    updatedIngredients[type] = currentCount - 1;
    const updatedPrice = this.state.price - INGREDIENT_PRICE[type];
    this.setState({ ingredients: updatedIngredients, price: updatedPrice });
    this.purchasable(updatedIngredients);
  }

  purchaseHandler = () => {
    this.setState({ purchasing: true });
  }

  purchaseCancelHandler = () => {
    this.setState({ purchasing: false });
  }

  purchaseContinueHandler = () => {
     const queryParams = [];
     for (let i in this.state.ingredients) {
       queryParams.push(encodeURIComponent(i) + '=' + encodeURIComponent(this.state.ingredients[i]));
     }
     queryParams.push('price=' + this.state.price);
     const queryString = queryParams.join('&');

     this.props.history.push({
       pathname: '/checkout',
       search: '?' + queryString
     });
  }

  render() {
    const disableRemove = { ...this.state.ingredients };
    for (let key in disableRemove) {
      disableRemove[key] = disableRemove[key] <= 0;
    }

    let orderSummary = null;
    let burger = this.state.error ? <p>Ingredients cannot be loaded!</p> : <Spinner />;

    if (this.state.ingredients) {
      orderSummary = <OrderSummary
        ingredients={this.state.ingredients}
        price={this.state.price}
        purchaseCancelled={this.purchaseCancelHandler}
        purchaseContinued={this.purchaseContinueHandler}
      />;

      burger =
        <Aux>
          <Burger ingredients={this.state.ingredients} />
          <BuildControls
            ingredientAdded={this.addIngredientHandler}
            ingredientRemoved={this.removeIngredientHandler}
            disableRemove={disableRemove}
            purchasable={this.state.purchasable}
            price={this.state.price}
            ordered={this.purchaseHandler}
          />
        </Aux>
    }

    if (this.state.loading) {
      orderSummary = <Spinner />;
    }

    return (
      <Aux>
        <Modal show={this.state.purchasing} modalClosed={this.purchaseCancelHandler}>
          {orderSummary}
        </Modal>
        {burger}
      </Aux>
    );
  };
}

export default withErrorHandler(BurgerBuilder, axios);