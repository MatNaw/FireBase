export class ViewportUtil {
  private static readonly MAX_MOBILE_WIDTH = 850;
  private static readonly MOBILE_DEVICES = /Android|webOS|SamsungBrowser|iPhone|iPad|iPod|BlackBerry|IEMobile|Opera Mini/i;

  public static isMobile(): boolean {
    return ViewportUtil.MOBILE_DEVICES.test(navigator.userAgent) ||
      'ontouchstart' in document.documentElement && window.innerWidth < ViewportUtil.MAX_MOBILE_WIDTH;
  }
}
