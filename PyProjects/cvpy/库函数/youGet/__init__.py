from you_get import common

uri = r'https://www.bilibili.com/video/BV1k7411x7gA/?spm_id_from=333.337.search-card.all.click&vd_source=847d777accdbcbe332222eecfdabb3be'
common.any_download(url=uri, info_only=False, output_dir=r'D:\project\study\cvpy\vodeos', merge=True, auto_rename=True,
                    stream_id='mp4')
