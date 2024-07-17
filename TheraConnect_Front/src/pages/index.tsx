import React from 'react';
import Link from 'next/link'
import Typist from 'react-typist-component';
// import { HistoryCard } from '@/components/HistoryCard';
import { theme } from '@/lib/const';
import { withTranslation, WithTranslation } from "react-i18next"

class HomeComponent extends React.Component<WithTranslation> {
  state = {
    roomIdText: '',
    cursor: "|"
  };
  constructor(props: any) {
    super(props);
  }
  handleRoomIdTextChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    this.setState({ roomIdText: event.target.value });
  };

// timer = setInterval(()=>{
//     this.setState({cursor: this.state.cursor === "|" ? "*" : "|"})
// }, 300);

  render() {
    const { t, i18n } = this.props;
    return (
        <div className='Home flex justify-center items-center text-center mx-auto h-full w-full'>
            <div className='flex flex-col text-center justify-center'>
                {
                    i18n.language=='en' ?
                    (
                        <div>
                            <div className='text-xl md:text-5xl mb-2 hidden sm:block'>
                                T<Typist startDelay={1000}  typingDelay={110} loop={true}  cursor={<span className='cursor'>{this.state.cursor}</span>}   >heraConnect <Typist.Delay ms={1500} /><Typist.Backspace count={18} /></Typist>
                            </div>
                            <div className='text-xl md:text-5xl mb-2 block sm:hidden'>
                                TheraConnect
                            </div>
                        </div>
                    ) : (
                        <div className='text-xl md:text-5xl mb-2 block'>
                            안녕하세요
                        </div>
                    )
                }
                <div className="mx-auto mt-8 max-w-xl sm:flex sm:gap-4 h-12">
                <input
                    placeholder= {t('room.roomName')}
                    value={this.state.roomIdText}
                    onChange={this.handleRoomIdTextChange}
                    className="w-48 rounded-lg border-gray-200 bg-white p-3 text-gray-700 shadow-sm transition focus:border-white focus:outline-none focus:ring focus: ring-secondary-focus"
                    style={{ marginRight: 10 }}
                />
                <Link href={`/${this.state.roomIdText}`} > 
                    <button
                        className=" font-bold btn-primary rounded-lg h-full w-20 border-none text-white"
                    >
                        👉 {t('Go')}
                    </button>
                </Link>
                </div>
            </div>
        </div>
    );
  }
}

export default withTranslation()(HomeComponent);