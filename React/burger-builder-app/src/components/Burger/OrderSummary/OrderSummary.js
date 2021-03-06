import React from 'react';

import Aux from '../../../hoc/Auxi/Auxi';
import Button from '../../UI/Button/Button';

const orderSummary = (props) => {

  const ingredientSummary = Object.keys(props.ingredients)
    .map(ingredientKey => {
      return <li key={ingredientKey}>
        <span style={{ transform: 'capitalize' }}>{ingredientKey}</span> : {props.ingredients[ingredientKey]}
      </li>
    });

  return <Aux>
    <h3>Your Order</h3>
    <p>A delicious burger with the following ingredients:</p>
    <ul>
      {ingredientSummary}
    </ul>
    <p><strong>Total Price: {props.price.toFixed(2)}</strong></p>
    <p>Continue to checkout?</p>
    <Button buttonType="Danger" clicked={props.purchaseCancelled}>CANCEL</Button>
    <Button buttonType="Success" clicked={props.purchaseContinued}>CONTINUE</Button>
  </Aux>
}

export default orderSummary;