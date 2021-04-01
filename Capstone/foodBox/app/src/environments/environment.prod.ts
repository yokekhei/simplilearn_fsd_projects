declare const window: any;

export const environment = {
  production: true,
  apiUrl: 'api',
  stripePublicKey: window.env.stripePublicKey
};
