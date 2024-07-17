
import type { AppProps } from 'next/app';
import '@livekit/components-styles';
// import '@/styles/components-styles';
import '@livekit/components-styles/prefabs';
// import '@/styles/general/index.css'
import '../styles/globals.css';
import { DefaultSeo } from 'next-seo';
import TopBar from '@/components/BarComponent/TopBar';
import Script from 'next/script';
import '../i18n/config'; 

function MyApp({ Component, pageProps }: AppProps) {
    return (
        <>
            <DefaultSeo
                title="TheraConnect Room"
                titleTemplate="%s"
                defaultTitle="TheraConnect Room"
                description="TheraConnect Room"
                twitter={{
                    handle: '@livekitted',
                    site: '@livekitted',
                    cardType: 'summary_large_image',
                }}
                openGraph={{
                    url: 'https://chat.cwy666.com',
                    images: [
                        {
                            url: 'https://meet.livekit.io/images/livekit-meet-open-graph.png',
                            width: 2000,
                            height: 1000,
                            type: 'image/png',
                        },
                    ],
                    site_name: 'TheraConnect Room',
                }}
                additionalMetaTags={[
                    {
                        property: 'theme-color',
                        content: '#070707',
                    },
                ]}
                additionalLinkTags={[
                    {
                        rel: 'icon',
                        href: '/favicon.ico',
                    },
                    {
                        rel: 'apple-touch-icon',
                        href: '/images/livekit-apple-touch.png',
                        sizes: '180x180',
                    },
                    {
                        rel: 'mask-icon',
                        href: '/images/livekit-safari-pinned-tab.svg',
                        color: '#070707',
                    },
                ]}
            />
            <TopBar />
            <Component {...pageProps} />
            {/* <Script src="https://www.WebRTC-Experiment.com/RecordRTC.js"></Script> */}
            {process.env.NEXT_PUBLIC_UMAMI_URL != undefined && process.env.NEXT_PUBLIC_UMAMI_URL != "" && (
                <Script
                    src={process.env.NEXT_PUBLIC_UMAMI_URL}
                    strategy='afterInteractive'
                    data-website-id={process.env.NEXT_PUBLIC_UMAMI_ID}
                />
            )}
            {
                process.env.NEXT_PUBLIC_USE_SHAREVIDEO && (
                    <Script type="text/javascript" src="https://cdn.staticfile.org/flv.js/1.6.2/flv.min.js"></Script>
                )
            }
            {
                process.env.NEXT_PUBLIC_USE_SHAREVIDEO && (
                    <Script type="text/javascript" src="https://npm.elemecdn.com/hls.js/dist/hls.min.js"></Script>
                )
            }
        </>
    );
}

export default MyApp;
