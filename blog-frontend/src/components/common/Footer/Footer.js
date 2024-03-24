import React from 'react';
import styles from './Footer.scss';
import classNames from 'classnames/bind';

const cx = classNames.bind(styles);

const Footer = () => (
  <footer className={cx('footer')}>
    <div className={cx('content')}>© 2024 Copyright - Dhaval-joshi and team</div>
  </footer>
);

export default Footer;