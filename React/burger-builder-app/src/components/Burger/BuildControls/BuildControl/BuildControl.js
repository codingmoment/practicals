import React from 'react';

import classes from './BuildControl.module.css';

const BuildControl = (props) => (
  <div className={classes.BuildControl}>
    <div className={classes.Label}>
      {props.ingredientLabel}
    </div>
    <button className={classes.Less} onClick={props.ingredientRemoved} disabled={props.disableRemove} >Less</button>
    <button className={classes.More} onClick={props.ingredientAdded}>More</button>
  </div>
);

export default BuildControl;