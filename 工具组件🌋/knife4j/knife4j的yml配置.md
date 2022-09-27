```yml
springdoc:
  packages-to-scan: com.yamcanda.icube.web.controller
  api-docs:
    enabled: true
#    path: /doc
#    groups:
#      enabled: true
  swagger-ui:
    enabled: true
    path: /docs
  show-actuator: true
  group-configs:  ## 文档 API 分组
#    - {group: 'api', paths-to-match: '/api/**'}
    - {group: 'common', paths-to-match: '/*'}
    - {group: 'system', paths-to-match: '/system/**'}
    - {group: 'sample', paths-to-match: '/sample/**'}
    - {group: 'quality', paths-to-match: '/quality/**'}
    - {group: 'file', paths-to-match: '/file/**'}
    - {group: 'env', paths-to-match: '/env/**'}
    - {group: 'stat', paths-to-match: '/stat/**'}
    - {group: 'exam', paths-to-match: '/exam/**'}
    - {group: 'test', paths-to-match: '/test/**'}
    - {group: 'pa', paths-to-match: '/pa/**'}
    - {group: 'eq', paths-to-match: '/eq/**'}
    - {group: 'bpm', paths-to-match: '/bpm/**'}
    - {group: 'flowable', paths-to-match: '/flowable/**'}

```
